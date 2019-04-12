# Preparation for the model running

**Before running the code, download the diversity file from the Baidu Cloud**  
Link: https://pan.baidu.com/s/1k23eRsMqoctRheuA13gTIw Password: c9jf 

Unzip the DiversityFiles into AcrossNetworkEmbeddingData dir. 

For instance "AcrossNetworkEmbeddingData/DiversityFiles/all_share_diversity.9.foldtrain.number" 

# Model  Runing

All the model files in FinalModel package, run IONE.java and IONEDiversity.java first, then several embedding file will be generated in foursquare/embeddings and twitter/embeddings. 

# Data description

Description of Data directory

AcrossNetworkEmbeddingData

	foursquare:
	
		following: the relation file, "1  2" means user 1 is the follower of user 2.  			   			
    
	twitter:
	
		the same as the foursquare fold
		
	twitter_foursquare_groundtruth:
	
		groundtruth: the groundtruth for our experiment, the anchor users between twitter and foursquare. Only column in which ids are appeared in both foursquare/following and twitter/following. 
    
		Note: 
		
		**pls make the anchors as the *same* id during the pre-preparation.**
		
		**Although the testing anchors have the same id, they will *not* take part in the training progress as they are not contained in the groundtruth.x.foldtrain.train file.**
		
		**There are several users have same ids (except for anchor users) in both foursquare/following and twitter/following files, but they are not the same user.**
		
		groundtruth.x.foldtrain.train, the traning anchors, which are the 0.x of all the anchors.
		
		groundtruth.x.foldtrain.test,  the testing anchors, which are the 1-0.x of all the anchors.
		
	DiversityFiles
	
		all_share_diversity.x.foldtrain.number, which are the 0.x of all the anchors. format:
		
		user_in_networkx user_in_networky|shared_anchor_user|diversity_value|relationship_type(follower/followee)

# Using other datasets for this model

Prepare your data format the same as twitter/following, foursquare/following and the twitter_foursquare_groundtruth.

## Generate the DiversityFiles

Run getSharedFileAllDiversity.py in AcrossNetworkEmbeddingData dir, make sure that you have the correct path for saving generate diversity files, eg. AcrossNetworkEmbeddingData/DiversityFiles/.

Note that, use py 2.7 for the enviornment, pip install python-louvain for community detection. Then you can run the model (The same as model running section). 






