# AcrossNetworkEmbedding
Source Code and data for IJCAI 2016 paper "Aligning Users Across Social Networks Using Network Embedding"

When you running the code, firstly check the Vars.java to make sure that the path of the data is correct.

Description of Data directory

   AcrossNetworkEmbeddingData
   
   		foursquare:
   		
   			following: the relation file, "uid1  uid2" means uid1 is the follower of uid2.
   			
   			following.0.x: following files with different interop (see the paper for details).
   			
   			following.reverse: the reverse relation file, for model which considers only one direction context
   			
   		twitter:
   		
   			the same as the foursquare fold
   			
   		twitter_foursquare_groundtruth:
   		
   			groundtruth: the groundtruth for our experiment, the anchor users between twitter and foursquare
   			
   			groundtruth.x.foldtrain.train, the traning anchors, which are the 0.x of all the anchors.
   			
   			groundtruth.x.foldtrain.test,  the testing anchors, which are the 1-0.x of all the anchors.
   			
   			groundtruth.test.linkp.x, the soft constraint file, which is predicted by a classifier. Only for the IONES model.

There are four models of our paper, INE, ONE , IONE and IONE-S

For the INE and ONE, run the INE.java. If you run the INE model, just use "test.Train(10000000, "", 100);", for ONE model,
modify the code as "test.Train(10000000, ".reverse", 100);".

For the IONE, run the IONE.java.

For the IONES, run the IONES.java

If you want to test the performance of the models with different interops, add the postfix to the Train function. For example,
"test.Train(10000000, ".0.1", 100);".

All the embeddings in the embedding directory of foursquare and twitter.

The getPrecision.java is used for p@1-p@30 calculation of our model。

Feel free to contact me (Liu Li liuli0407@hotmail.com) when you have any problems about the paper or the code. 
