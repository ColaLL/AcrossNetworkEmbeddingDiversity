package FinalModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

import ModelWith2OrderNorm.BasicUnit;
import StaticVar.Vars;

public class ConcatenateAnswer {
	
	
	public void get_IONE_Con_twitter(int dimension,int train_ratio) throws IOException
	{
		HashMap<String,String> twitter_embedding=new HashMap<String,String>();
		
		String twitter_embedding_file=Vars.twitter_dir+"/twitter/embeddings/"
				+"twitter.embedding.update.2SameAnchor.twodirectionContext."+dimension+"_dim."+train_ratio+".foldtrain.10000000";
		String twitter_embedding_diversity_file=Vars.twitter_dir+"/twitter/embeddings/"
				+"twitter.embedding.update.2SameAnchor.twodirectionContext."+dimension+"_dim."+train_ratio+".foldtrain.diversity.100000000";
		String twitter_embedding_diversity_concatenate_file=Vars.twitter_dir+"/twitter/embeddings/"
				+"twitter.embedding.update.2SameAnchor.concatenateDiversityTwodirectionContext."+dimension+"_dim."+train_ratio+".foldtrain.diversity.100000000";

		
		BufferedWriter bw_twitter=BasicUnit.writerData(twitter_embedding_diversity_concatenate_file);
		BufferedReader br=BasicUnit.readData(twitter_embedding_file);
		String temp_string=br.readLine();
		while(temp_string!=null)
		{
			String[] array=temp_string.split("\\s+");
			twitter_embedding.put(array[0], array[1]);
			temp_string=br.readLine();
		}
		br.close();
		br=BasicUnit.readData(twitter_embedding_diversity_file);
		temp_string=br.readLine();
		while(temp_string!=null)
		{
			String[] array=temp_string.split("\\s+");
			bw_twitter.write(temp_string+twitter_embedding.get(array[0])+"\n");
			temp_string=br.readLine();
		}
		br.close();
		bw_twitter.flush();
		bw_twitter.close();
								
	}
	
	public void get_IONE_Con_foursquare(int dimension,int train_ratio) throws IOException
	{
		HashMap<String,String> twitter_embedding=new HashMap<String,String>();
		
		String twitter_embedding_file=Vars.twitter_dir+"/foursquare/embeddings/"
				+"foursquare.embedding.update.2SameAnchor.twodirectionContext."+dimension+"_dim."+train_ratio+".foldtrain.10000000";
		String twitter_embedding_diversity_file=Vars.twitter_dir+"/foursquare/embeddings/"
				+"foursquare.embedding.update.2SameAnchor.twodirectionContext."+dimension+"_dim."+train_ratio+".foldtrain.diversity.100000000";
		String twitter_embedding_diversity_concatenate_file=Vars.twitter_dir+"/foursquare/embeddings/"
				+"foursquare.embedding.update.2SameAnchor.concatenateDiversityTwodirectionContext."+dimension+"_dim."+train_ratio+".foldtrain.diversity.100000000";


		BufferedWriter bw_twitter=BasicUnit.writerData(twitter_embedding_diversity_concatenate_file);
		BufferedReader br=BasicUnit.readData(twitter_embedding_file);
		String temp_string=br.readLine();
		while(temp_string!=null)
		{
			String[] array=temp_string.split("\\s+");
			twitter_embedding.put(array[0], array[1]);
			temp_string=br.readLine();
		}
		br.close();
		br=BasicUnit.readData(twitter_embedding_diversity_file);
		temp_string=br.readLine();
		while(temp_string!=null)
		{
			String[] array=temp_string.split("\\s+");
			bw_twitter.write(temp_string+twitter_embedding.get(array[0])+"\n");
			temp_string=br.readLine();
		}
		br.close();
		bw_twitter.flush();
		bw_twitter.close();						
	}
	
	
	public void ConcatenateIONEandIONEDiversity_twitter(int dimension,int train_ratio) throws IOException
	{
		HashMap<String,String> twitter_embedding=new HashMap<String,String>();
		
		String twitter_embedding_file=Vars.twitter_dir+"/twitter/embeddings/"
				+"twitter.embedding.update.2SameAnchor.concatenate."+dimension+"_dim."+train_ratio+".foldtrain.10000000";
		String twitter_embedding_diversity_file=Vars.twitter_dir+"/twitter/embeddings/"
				+"twitter.embedding.update.2SameAnchor.concatenate."+dimension+"_dim."+train_ratio+".foldtrain.diversity.100000000";
		String twitter_embedding_diversity_concatenate_file=Vars.twitter_dir+"/twitter/embeddings/"
				+"twitter.embedding.update.2SameAnchor.concatenateDiversity."+dimension+"_dim."+train_ratio+".foldtrain.diversity.100000000";

		
		BufferedWriter bw_twitter=BasicUnit.writerData(twitter_embedding_diversity_concatenate_file);
		BufferedReader br=BasicUnit.readData(twitter_embedding_file);
		String temp_string=br.readLine();
		while(temp_string!=null)
		{
			String[] array=temp_string.split("\\s+");
			twitter_embedding.put(array[0], array[1]);
			temp_string=br.readLine();
		}
		br.close();
		br=BasicUnit.readData(twitter_embedding_diversity_file);
		temp_string=br.readLine();
		while(temp_string!=null)
		{
			String[] array=temp_string.split("\\s+");
			bw_twitter.write(temp_string+twitter_embedding.get(array[0])+"\n");
			temp_string=br.readLine();
		}
		br.close();
		bw_twitter.flush();
		bw_twitter.close();
								
	}
	
	public void ConcatenateIONEandIONEDiversity_foursquare(int dimension,int train_ratio) throws IOException
	{
		HashMap<String,String> twitter_embedding=new HashMap<String,String>();
		
		String twitter_embedding_file=Vars.twitter_dir+"/foursquare/embeddings/"
				+"foursquare.embedding.update.2SameAnchor.concatenate."+dimension+"_dim."+train_ratio+".foldtrain.10000000";
		String twitter_embedding_diversity_file=Vars.twitter_dir+"/foursquare/embeddings/"
				+"foursquare.embedding.update.2SameAnchor.concatenate."+dimension+"_dim."+train_ratio+".foldtrain.diversity.100000000";
		String twitter_embedding_diversity_concatenate_file=Vars.twitter_dir+"/foursquare/embeddings/"
				+"foursquare.embedding.update.2SameAnchor.concatenateDiversity."+dimension+"_dim."+train_ratio+".foldtrain.diversity.100000000";

		BufferedWriter bw_twitter=BasicUnit.writerData(twitter_embedding_diversity_concatenate_file);
		BufferedReader br=BasicUnit.readData(twitter_embedding_file);
		String temp_string=br.readLine();
		while(temp_string!=null)
		{
			String[] array=temp_string.split("\\s+");
			twitter_embedding.put(array[0], array[1]);
			temp_string=br.readLine();
		}
		br.close();
		br=BasicUnit.readData(twitter_embedding_diversity_file);
		temp_string=br.readLine();
		while(temp_string!=null)
		{
			String[] array=temp_string.split("\\s+");
			bw_twitter.write(temp_string+twitter_embedding.get(array[0])+"\n");
			temp_string=br.readLine();
		}
		br.close();
		bw_twitter.flush();
		bw_twitter.close();						
	}
	
	
	public static void main(String[] args) throws IOException
	{
		ConcatenateAnswer new_test=new ConcatenateAnswer();
		for (int train_ratio=9;train_ratio<10;train_ratio++)
		{
			for(int i=100;i<=100;i++)
			{
				new_test.ConcatenateIONEandIONEDiversity_foursquare(i,train_ratio);;
				new_test.ConcatenateIONEandIONEDiversity_twitter(i,train_ratio);
				//new_test.get_IONE_Con_twitter(i, train_ratio);
				//new_test.get_IONE_Con_foursquare(i, train_ratio);
			}
		}

	}

}
