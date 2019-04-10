package FinalModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import ModelWith2OrderNorm.BasicUnit;
import StaticVar.Vars;

public class IONE {
	
	int fold;
	
	public IONE(int fold)
	{
		this.fold=fold;
	}
	
	//network x as the foursquare, network y as the twitter 
	
	public HashMap<String,String> getNetworkAnchors(String postfix_1,String postfix_2) throws IOException
	{
		HashMap<String,String> answer_map=new HashMap<String,String>();
		String file_name=Vars.twitter_dir+"twitter_foursquare_groundtruth/groundtruth."+this.fold+".foldtrain.train.number";
		BufferedReader br=BasicUnit.readData(file_name);
		String temp_string=br.readLine();
		while(temp_string!=null)
		{
			answer_map.put(temp_string+postfix_1, temp_string+postfix_2);
			temp_string=br.readLine();
		}
		return answer_map;
	}
	
	
	public void Train(int total_iter,String fileInterop,int dim) throws IOException
	{

		String run_count=this.fold+".foldtrain";
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
		
		IONEUpdate twoOrder_x=
				new IONEUpdate(dim,last_file,"foursquare");
		twoOrder_x.init();
		
		IONEUpdate twoOrder_y=
				new IONEUpdate(dim,myspace_file,"twitter");
		twoOrder_y.init();
		
		HashMap<String,String> lastfm_anchor=getNetworkAnchors("_foursquare","_twitter");
		HashMap<String,String> myspace_anchor=getNetworkAnchors("_twitter","_foursquare");
		System.out.println(lastfm_anchor.size());
		System.out.println(myspace_anchor.size());
		
		for(int i=0;i<total_iter;i++)
		{
			twoOrder_x.Train(i, total_iter, twoOrder_x.answer,
					twoOrder_x.answer_context_input,
					twoOrder_x.answer_context_output,
					lastfm_anchor);
			twoOrder_y.Train(i, total_iter, twoOrder_x.answer,
					twoOrder_x.answer_context_input,
					twoOrder_x.answer_context_output,
					myspace_anchor);
			if((i+1)%10000000==0)
			{
				twoOrder_x.output(ouput_filename_last+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_y.output(ouput_filename_myspace+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_x.output_input_context(ouput_filename_networkx_input_context+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_y.output_input_context(ouput_filename_networky_input_context+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_x.output_output_context(ouput_filename_networkx_output_context+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_y.output_output_context(ouput_filename_networky_output_context+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_x.output_concatenate(ouput_filename_networkx_concatenate+"."+dim+"_dim."+run_count+"."+(i+1));
				twoOrder_y.output_concatenate(ouput_filename_networky_concatenate+"."+dim+"_dim."+run_count+"."+(i+1));
		
			}
		}
	}
	public static void main(String[] args) throws IOException
	{
		for(int i=9;i<10;i++)
		{
			IONE test=
					new IONE(i);
			for(int j=100;j<=100;j+=1)
			{
				test.Train(10000000, "", j);
			}
		}
	}
	

}
