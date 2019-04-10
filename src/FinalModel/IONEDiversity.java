package FinalModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import ModelWith2OrderNorm.BasicUnit;
import StaticVar.Vars;

public class IONEDiversity {
	
	int fold;
	
	Random rnd=new Random(123);

	public IONEDiversity(int fold)
	{
		this.fold=fold;
	}
	
	public HashMap<String,String> getLastFMAnchors() throws IOException
	{
		HashMap<String,String> answer_map=new HashMap<String,String>();
		String file_name=Vars.twitter_dir+"twitter_foursquare_groundtruth/"
				+ "groundtruth."+this.fold+".foldtrain.train.number";
		BufferedReader br=BasicUnit.readData(file_name);
		String temp_string=br.readLine();
		while(temp_string!=null)
		{
			answer_map.put(temp_string+"_foursquare", temp_string+"_twitter");
			temp_string=br.readLine();
		}
		return answer_map;
	}
	
	public HashMap<String,String> getMyspaceAnchors() throws IOException
	{
		HashMap<String,String> answer_map=new HashMap<String,String>();
		String file_name=Vars.twitter_dir+"twitter_foursquare_groundtruth/"
				+ "groundtruth."+this.fold+".foldtrain.train.number";
		BufferedReader br=BasicUnit.readData(file_name);
		String temp_string=br.readLine();
		while(temp_string!=null)
		{
			answer_map.put(temp_string+"_twitter", temp_string+"_foursquare");
			temp_string=br.readLine();
		}
		return answer_map;
	}
	public void Train(int total_iter,String fileInterop,int dim) throws IOException
	{
		String run_count=this.fold+".foldtrain.diversity";
		String last_file=
				StaticVar.Vars.twitter_dir+"/foursquare/following.number"+fileInterop;
		String ouput_filename_last=
				StaticVar.Vars.twitter_dir+
				"/foursquare/embeddings/foursquare.embedding.update.2SameAnchor.twodirectionContext"+fileInterop;
		
		String myspace_file=
				StaticVar.Vars.twitter_dir+"/twitter/following.number"+fileInterop;
		String ouput_filename_myspace=
				StaticVar.Vars.twitter_dir+
				"/twitter/embeddings/twitter.embedding.update.2SameAnchor.twodirectionContext"+fileInterop;
		
		
		String ouput_filename_networkx_input_context=
				StaticVar.Vars.twitter_dir+
				"/foursquare/embeddings/foursquare.embedding.update.2SameAnchor.InputContextVector"+fileInterop;
		String ouput_filename_networkx_output_context=
				StaticVar.Vars.twitter_dir+
				"/foursquare/embeddings/foursquare.embedding.update.2SameAnchor.OutputContextVector"+fileInterop;		
		String ouput_filename_networkx_concatenate=
				StaticVar.Vars.twitter_dir+
				"/foursquare/embeddings/foursquare.embedding.update.2SameAnchor.concatenate"+fileInterop;
		
		String ouput_filename_networky_input_context=
				StaticVar.Vars.twitter_dir+
				"/twitter/embeddings/twitter.embedding.update.2SameAnchor.InputContextVector"+fileInterop;	
		String ouput_filename_networky_output_context=
				StaticVar.Vars.twitter_dir+
				"/twitter/embeddings/twitter.embedding.update.2SameAnchor.OutputContextVector"+fileInterop;		
		String ouput_filename_networky_concatenate=
				StaticVar.Vars.twitter_dir+
				"/twitter/embeddings/twitter.embedding.update.2SameAnchor.concatenate"+fileInterop;
		
		
		String diversity_file=StaticVar.Vars.twitter_dir+"DiversityFiles/all_share_diversity."+this.fold+".foldtrain.number";


		IONEDiversityUpdate twoOrder_last=
				new IONEDiversityUpdate(dim,last_file,"foursquare");
		twoOrder_last.init();
		
		IONEDiversityUpdate twoOrder_myspace=
				new IONEDiversityUpdate(dim,myspace_file,"twitter");
		twoOrder_myspace.init();
		
		IONEDiversityUpdateNetwork diversity=
				new IONEDiversityUpdateNetwork(dim,diversity_file,"");
		diversity.init();
				
		HashMap<String,String> lastfm_anchor=getLastFMAnchors();
		HashMap<String,String> myspace_anchor=getMyspaceAnchors();
		System.out.println(lastfm_anchor.size());
		System.out.println(myspace_anchor.size());
		
		for(int i=0;i<total_iter;i++)
		{			
			double random1=rnd.nextDouble();
			double random2=rnd.nextDouble();
			int edge_id=(int) diversity.sampleAnEdge(random1,random2);
			String uid_1=diversity.source_id.get(edge_id);
			String uid_2=diversity.target_id.get(edge_id);
			String[] uid_2_array=uid_2.split("\\|");
			
			if(i%1000000==0)
			{				
				twoOrder_last.rho=twoOrder_last.init_rho*(1.0-(double)i/(double)total_iter);
				if(twoOrder_last.rho<twoOrder_last.init_rho*0.0001) twoOrder_last.rho=twoOrder_last.init_rho*0.0001;
				System.out.println(i+" "+twoOrder_last.rho);
			}
			double diversity_value_iter=Double.parseDouble(uid_2_array[2]);	
			String foursquare_user=uid_1;
			String anchor_name=uid_2_array[1];
			String twitter_user=uid_2_array[0];
			{
				if(uid_2_array[3].equals("follower"))
				{
					{
						twoOrder_last.TrainWithDiversityFollower(i, total_iter, twoOrder_last.answer,
								twoOrder_last.answer_context_input,
								twoOrder_last.answer_context_output,
								lastfm_anchor,foursquare_user,anchor_name+"_foursquare",diversity_value_iter);
						twoOrder_myspace.TrainWithDiversityFollower(i, total_iter, twoOrder_last.answer,
								twoOrder_last.answer_context_input,
								twoOrder_last.answer_context_output,
								myspace_anchor,twitter_user,anchor_name+"_twitter",diversity_value_iter);
					}
				}
				else
				{
					{
						twoOrder_last.TrainWithDiversityFollower(i, total_iter, twoOrder_last.answer,
								twoOrder_last.answer_context_input,
								twoOrder_last.answer_context_output,
								lastfm_anchor,anchor_name+"_foursquare",foursquare_user,diversity_value_iter);
						twoOrder_myspace.TrainWithDiversityFollower(i, total_iter, twoOrder_last.answer,
								twoOrder_last.answer_context_input,
								twoOrder_last.answer_context_output,
								myspace_anchor,anchor_name+"_twitter",twitter_user,diversity_value_iter);
					}
				}
			}			
			if((i+1)%100000000==0)
			{
				twoOrder_last.output(ouput_filename_last+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_myspace.output(ouput_filename_myspace+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_last.output_input_context(ouput_filename_networkx_input_context+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_myspace.output_input_context(ouput_filename_networky_input_context+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_last.output_output_context(ouput_filename_networkx_output_context+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_myspace.output_output_context(ouput_filename_networky_output_context+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_last.output_concatenate(ouput_filename_networkx_concatenate+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_myspace.output_concatenate(ouput_filename_networky_concatenate+"."+dim+"_dim."+run_count+"."+(i+1));
			}
		}
	}
	public static void main(String[] args) throws IOException
	{
		for(int i=9;i<10;i++)
		{
			IONEDiversity test=
					new IONEDiversity(i);
			for(int j=100;j<=100;j+=1)
			{
				test.Train(100000000, "", j);
			}
		}
	}
	

}
