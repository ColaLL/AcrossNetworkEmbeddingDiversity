# Preparation for the model running

**Before running the code, download the diversity file from the Baidu Cloud**  
Link: https://pan.baidu.com/s/1k23eRsMqoctRheuA13gTIw Password: c9jf 

Unzip the DiversityFiles into AcrossNetworkEmbeddingData dir. 

For instance "AcrossNetworkEmbeddingData/DiversityFiles/all_share_diversity.9.foldtrain.number" 

# Model  Runing

# Data description

Description of Data directory

AcrossNetworkEmbeddingData

	foursquare:
	
		following: the relation file, "1  2" means user 1 is the follower of user 2.  			   			
    
	twitter:
	
		the same as the foursquare fold
		
	twitter_foursquare_groundtruth:
	
		groundtruth: the groundtruth for our experiment, the anchor users between twitter and foursquare. 
    
		Note: 
		
		**pls make the anchors as the *same* id during the pre-preparation.**
		
		**Although the testing anchors have the same id, they will *not* take part in the training progress as they are not contained in the groundtruth.x.foldtrain.train file.**	
		
		groundtruth.x.foldtrain.train, the traning anchors, which are the 0.x of all the anchors.
		
		groundtruth.x.foldtrain.test,  the testing anchors, which are the 1-0.x of all the anchors.
		
	DiversityFiles
		

# Using other datasets for this model

## Generate the DiversityFiles

