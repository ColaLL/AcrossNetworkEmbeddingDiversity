from __future__ import division
import networkx as nx
import community

train_anchor=set()

#fold_train=9

for fold_train in range(1,10):

    train_file=open('AcrossNetworkEmbeddingData/twitter_foursquare_groundtruth/'
                    'groundtruth.'+str(fold_train)+'.foldtrain.train.number')
    for lines in train_file.readlines():
        train_anchor.add(lines.strip())

    foursquare_network=nx.read_edgelist('AcrossNetworkEmbeddingData/foursquare/following.number',create_using=nx.DiGraph())

    twitter_network=nx.read_edgelist('AcrossNetworkEmbeddingData/twitter/following.number',create_using=nx.DiGraph())

    G=nx.read_edgelist('AcrossNetworkEmbeddingData/foursquare/following.number',create_using=nx.Graph())


    G=G.subgraph(train_anchor)

    #first compute the best partition
    partition = community.best_partition(G)

    share_follower_file=open('AcrossNetworkEmbeddingData/DiversityFiles/all_share_diversity.'
                             +str(fold_train)+'.foldtrain.number','w')

    for foursquare_user in foursquare_network.nodes():
        for twitter_user in twitter_network.nodes():

            followee_f = set(foursquare_network.successors(foursquare_user))
            followee_t = set(twitter_network.successors(twitter_user))
            shared = followee_f.intersection(followee_t).intersection(train_anchor)
            community_count_follower=set()
            for user in shared:
                community_count_follower.add(partition[user])
            follower_diversity = len(community_count_follower)

            if len(shared)!=0:
                for share_user in shared:
                    share_follower_file.write(foursquare_user+'_foursquare'+' '
                                              +twitter_user+'_twitter'+'|'+share_user
                                              +'|'+str(follower_diversity)+'|followee\n')


    for foursquare_user in foursquare_network.nodes():
        for twitter_user in twitter_network.nodes():
            followee_f = set(foursquare_network.predecessors(foursquare_user))
            followee_t = set(twitter_network.predecessors(twitter_user))
            shared = followee_f.intersection(followee_t).intersection(train_anchor)
            community_count_follower=set()
            for user in shared:
                community_count_follower.add(partition[user])
            follower_diversity = len(community_count_follower)

            if len(shared)!=0:
                for share_user in shared:
                    share_follower_file.write(foursquare_user+'_foursquare'+' '
                                              +twitter_user+'_twitter'+'|'+share_user
                                              +'|'+str(follower_diversity)+'|follower\n')



    share_follower_file.flush()
    share_follower_file.close()



