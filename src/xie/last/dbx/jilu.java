package xie.last.dbx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class jilu extends ListActivity {
	
	 public void onCreate(Bundle savedInstanceState){
	        super.onCreate(savedInstanceState);
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	      
	     setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData()));
	     
	    }
	 protected void onListItemClick(ListView l, View v, int position, long id) {  
		 Intent dakaijilu = new Intent();
		 Bundle chuandi=new Bundle();
		
		 chuandi.putInt("diji",position);
		 dakaijilu.putExtras(chuandi);
		 
		 setResult(1,dakaijilu);
		 finish();
			//dakaijilu.setClass(jilu.this,First.class);
		
			//startActivity(dakaijilu);
		 
		

		 
	    }  
	   
	    private List<String> getData(){
	         
	        List<String> data = new ArrayList<String>();
	        try {
	        	Context cont = this.getApplicationContext();
	        	File cunchujuti= new File(cont.getFilesDir()+"/xietempjuti.txt");
				FileReader input;
				input=new FileReader(cunchujuti);
				BufferedReader br=new BufferedReader(input);
    			String a=new String();
    			
    			a=br.readLine();
    			while(a!=null){
    			
    				String[] array;
    				array=a.split(";"); 
    			    data.add(array[0]+"  "+array[2]+"  "+array[3]);
    				a=br.readLine();
    			}
    			
    			br.close();
    			input.close();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	       
	         
	        return data;
	    }
}
