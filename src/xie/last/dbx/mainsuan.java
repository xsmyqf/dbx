package xie.last.dbx;

import java.util.Random;

import android.util.Log;



public class mainsuan {
	 int MAXNUM;
	 int MAXSHANGXIAN;
	final double PI=3.1415926;
	zhanchuli zhan[];
	int firstdealshu[];//={0,-2,4,2,2,-5};
	char firstdealfu[];//={' ','*','+','*','+',' '};
	chuli biaozhi[][];
	public chuli2 jieguo[][];
	int xiabiao=1;
	public int hebing[];

	mainsuan(int maxnu,int tempshang){
		MAXNUM=maxnu;
		zhan=new zhanchuli[MAXNUM*2+1];
		biaozhi=new chuli[MAXNUM+1][MAXNUM+1];
		jieguo=new chuli2[MAXNUM+1][MAXNUM+1];
		
		hebing=new int[MAXNUM];
		hebing[0]=1;
		firstdealshu=new int[MAXNUM+1];
		firstdealfu=new char[MAXNUM+1];
		MAXSHANGXIAN=tempshang;
		//mainsuanrukou();
	//firststage();
	}
void firststage()
{int i;
	Random shu1=new Random();
	Random shu2=new Random();
	for(i=1;i<=MAXNUM;i++)
	{
		firstdealshu[i]=shu1.nextInt()%MAXSHANGXIAN;
		if(shu2.nextInt()%2==0)
			firstdealfu[i]='+';
		else firstdealfu[i]='*';
		
	}
	//firstdealfu[MAXNUM]=' ';
//	Log.i("hello","xiexie");
}
String daochu(){
	String a="";
	int i;
	a=a+MAXNUM+";"+MAXSHANGXIAN+";";
	for(i=1;i<=MAXNUM;i++)
	a=a+firstdealshu[i]+"=";
	for(i=1;i<=MAXNUM;i++)
		a=a+firstdealfu[i]+"=";
	return a;
}
void daoru(String a){
	int i;
	String[] splitted = a.split("=");
	for(i=1;i<=MAXNUM;i++)
		firstdealshu[i]=Integer.parseInt(splitted[i-1]);
	for(i=1;i<=MAXNUM;i++)
		firstdealfu[i]=splitted[MAXNUM+i-1].charAt(0);
}
	double yunsuan(char fuhao,double a,double b)
		{
			switch(fuhao)
				{
					case '+':
					return(a+b);
					case '*':
					return(a*b);
				}
			return 0;
		}

	void dayin(int x,int y,int flagbenci)
		{int flagxuanze=0;
		int templeftx=0,templefty=0,temprightx=0,temprighty=0;
				if(x==y)return;
				zhan[xiabiao]=new zhanchuli();
				if(flagbenci==1)
				{
					zhan[xiabiao].shu=biaozhi[x][y].maxlefty;
					flagxuanze=biaozhi[x][y].flagmax;
					templeftx=biaozhi[x][y].maxleftx;
					templefty=biaozhi[x][y].maxlefty;
					temprightx=biaozhi[x][y].maxrightx;
					temprighty=biaozhi[x][y].maxrighty;
				}
				if(flagbenci==2)
				{
					zhan[xiabiao].shu=biaozhi[x][y].minlefty;
					flagxuanze=biaozhi[x][y].flagmin;
					templeftx=biaozhi[x][y].minleftx;
					templefty=biaozhi[x][y].minlefty;
					temprightx=biaozhi[x][y].minrightx;
					temprighty=biaozhi[x][y].minrighty;
				}
				zhan[xiabiao].flag=flagbenci;
				//printf("%d %d -%d  %d\n",zhan[xiabiao].leftx,zhan[xiabiao].lefty,zhan[xiabiao].rightx,zhan[xiabiao].righty);
				//printf("   %d \n",xiabiao);
				xiabiao++;

				switch(flagxuanze){
				case 1:
					dayin(templeftx,templefty,1);
					dayin(temprightx,temprighty,1);
					return;
				case 2:
					dayin(templeftx,templefty,2);
					dayin(temprightx,temprighty,2);
					return;
				case 3:
					dayin(templeftx,templefty,1);
					dayin(temprightx,temprighty,2);
					return;
				case 4:
					dayin(templeftx,templefty,2);
					dayin(temprightx,temprighty,1);
					return;
				}
				zhan[0]=new zhanchuli();
				zhan[0].shu=xiabiao;
				//printf("%d  \n",zhan[0].max);
		}

	void chulijieguo()
		{int i,j,m,n=0;
		int zonghe=0;
		double zanshishu=0;
	 //   printf("%d\n",biaozhi[1][MAXNUM].max);
	    dayin(1,MAXNUM,1);

	  /*  for(i=zhan[0].max-1;i>=1;i--)
	    {
	    	System.out.printf("%d   %d\n",zhan[i].lefty,zhan[i].rightx);
	    }*/

	    for(i=1;i<=MAXNUM;i++)
	    {m=1;
	        for(j=1;j<=MAXNUM-i+1;j++)
	        {jieguo[i][j]=new chuli2();
	            if(i==1){
	            			   jieguo[i][j].left=j;
	                        jieguo[i][j].right=j;
	                        jieguo[i][j].shu=biaozhi[j][j].maxshu;
	                        jieguo[i][j].fu=firstdealfu[j];
	                        jieguo[i][j].shutheata=2*PI/MAXNUM*(j-1);
	                        jieguo[i][j].futheata=jieguo[i][j].shutheata+2*PI/MAXNUM/2;
	                    }
	            if(i>1){
	        //    	if(zhan[MAXNUM-i+1].flagmax==1 || zhan[MAXNUM-i+1].flagmax ==3)zonghe=zhan[MAXNUM-i+1].maxlefty;
	          //  	if(zhan[MAXNUM-i+1].flagmax==2 || zhan[MAXNUM-i+1].flagmax ==4)zonghe=zhan[MAXNUM-i+1].minlefty;

	            	if(zhan[MAXNUM-i+1].shu!=jieguo[i-1][j].right){

	                	jieguo[i][j].fu=jieguo[i-1][m].fu;//终于知道了，这是传递的指针，不是实体。
	                    jieguo[i][j].shutheata=jieguo[i-1][m].shutheata;
	                    jieguo[i][j].shu=jieguo[i-1][m].shu;
	                    jieguo[i][j].futheata=jieguo[i-1][m].futheata;
	                    jieguo[i][j].left=jieguo[i-1][m].left;
	                    jieguo[i][j].right=jieguo[i-1][m].right;
	                    m++;
	                }
	                if(zhan[MAXNUM-i+1].shu==jieguo[i-1][j].right){
	                    n=j;
	                    	hebing[i-1]=n;
	                    if(zhan[MAXNUM-i+1].flag==1)zanshishu=biaozhi[jieguo[i-1][m].left][jieguo[i-1][m+1].right].maxshu;
	                    if(zhan[MAXNUM-i+1].flag==2)zanshishu=biaozhi[jieguo[i-1][m].left][jieguo[i-1][m+1].right].minshu;
	                    jieguo[i][j].shu=zanshishu;
	                    jieguo[i][j].shutheata=((jieguo[i-1][m+1].shutheata-jieguo[i-1][m].shutheata)/2+jieguo[i-1][m].shutheata);
	                    jieguo[i][j].left=jieguo[i-1][m].left;
	                    jieguo[i][j].right=jieguo[i-1][m+1].right;
	                    jieguo[i][j].fu=jieguo[i-1][m+1].fu;

	                m=m+2;
	                }
	                    }

	//std::cout<<jieguo[i][j].max<<"    "<<jieguo[i][j].left<<"       "<<jieguo[i][j].right<<"   "<<jieguo[i][j].fu<<"    "<<jieguo[i][j].futheata<<"\n";
	        }
	      if(i!=MAXNUM){
	        if(n==1){jieguo[i][1].futheata=(jieguo[i][2].shutheata-jieguo[i][1].shutheata)/2+jieguo[i][1].shutheata;}
	        if(n==MAXNUM-i+1){jieguo[i][n-1].futheata=(jieguo[i][n].shutheata-jieguo[i][n-1].shutheata)/2+jieguo[i][n-1].shutheata;}
	      }
	        if((n>1)&&(n<MAXNUM-i+1)){jieguo[i][n-1].futheata=(jieguo[i][n].shutheata-jieguo[i][n-1].shutheata)/2+jieguo[i][n-1].shutheata;
	                                  jieguo[i][n].futheata=(jieguo[i][n+1].shutheata-jieguo[i][n].shutheata)/2+jieguo[i][n].shutheata;
	                                    }
	    }

		}

	void mainsuanrukou()
	{
	    int i,j,meici;
	    double tempshu1,tempshu2,tempshu3,tempshu4,tempmax=-Double.MAX_VALUE,tempmin=Double.MAX_VALUE;
	    double maxtemp=-Double.MAX_VALUE,mintemp=Double.MAX_VALUE;
	    int tempbiaozhi1=0,tempbiaozhi2=0;
	    //printf("%d\n",yunsuan('+',2,3));
	    //System.out.printf("%d\n",firstdealshu[5]);
	    //init biaozhi
	    for(i=1;i<=MAXNUM;i++)
	           {
	    	biaozhi[i][i]=new chuli();
	
	    biaozhi[i][i].setmax(firstdealshu[i],i,i,0,0);
	    biaozhi[i][i].setmin(firstdealshu[i],i,i,0,0);
	    if(i!=MAXNUM){
	    	biaozhi[i][i+1]=new chuli();
	   /* biaozhi[i][i+1].max=yunsuan(firstdealfu[i],firstdealshu[i],firstdealshu[i+1]);
	    biaozhi[i][i+1].leftx=i;
	    biaozhi[i][i+1].lefty=i;
	    biaozhi[i][i+1].rightx=i+1;
	    biaozhi[i][i+1].righty=i+1;*/
	    biaozhi[i][i+1].setmax(yunsuan(firstdealfu[i],firstdealshu[i],firstdealshu[i+1]),i,i,i+1,i+1);
	    biaozhi[i][i+1].flagmax=1;
	    biaozhi[i][i+1].setmin(yunsuan(firstdealfu[i],firstdealshu[i],firstdealshu[i+1]),i,i,i+1,i+1);
	    biaozhi[i][i+1].flagmin=2;
	    }

	           }

	    //jisuan
	if(MAXNUM>=3){
	    for(i=1;i<=MAXNUM-2;i++)
	    	for(j=1;j<=MAXNUM-1-i;j++)
	          {//biaozhi[j,j+i+1]
	    		biaozhi[j][j+i+1]=new chuli();
	    			for(meici=1;meici<=i+1;meici++){
	    				
	    						{maxtemp=-Double.MAX_VALUE;
	    						 mintemp=Double.MAX_VALUE;
	    						 tempbiaozhi1=0;tempbiaozhi2=0;
	    								tempshu1=yunsuan(firstdealfu[j+meici-1],biaozhi[j][j+meici-1].maxshu,biaozhi[j+meici][j+i+1].maxshu);
	    									if(maxtemp<tempshu1){maxtemp=tempshu1;tempbiaozhi1=1;}
	    									if(mintemp>tempshu1){mintemp=tempshu1;tempbiaozhi2=1;}
	    								tempshu2=yunsuan(firstdealfu[j+meici-1],biaozhi[j][j+meici-1].minshu,biaozhi[j+meici][j+i+1].minshu);
	    									if(maxtemp<tempshu2){maxtemp=tempshu2;tempbiaozhi1=2;}
	    									if(mintemp>tempshu2){mintemp=tempshu2;tempbiaozhi2=2;}
	    								tempshu3=yunsuan(firstdealfu[j+meici-1],biaozhi[j][j+meici-1].maxshu,biaozhi[j+meici][j+i+1].minshu);
	    									if(maxtemp<tempshu3){maxtemp=tempshu3;tempbiaozhi1=3;}
	    									if(mintemp>tempshu3){mintemp=tempshu3;tempbiaozhi2=3;}
	    								tempshu4=yunsuan(firstdealfu[j+meici-1],biaozhi[j][j+meici-1].minshu,biaozhi[j+meici][j+i+1].maxshu);
	    									if(maxtemp<tempshu4){maxtemp=tempshu4;tempbiaozhi1=4;}
	    									if(mintemp>tempshu4){mintemp=tempshu4;tempbiaozhi2=4;}
	    						}
	    					if(maxtemp>tempmax){
	    							
	    							tempmax=maxtemp;
	    							biaozhi[j][j+i+1].flagmax=tempbiaozhi1;
	    							biaozhi[j][j+i+1].setmax(tempmax,j,j+meici-1,j+meici,j+i+1);
	    					}
	    					if(mintemp<tempmin){
	    							tempmin=mintemp;
	    							biaozhi[j][j+i+1].flagmin=tempbiaozhi2;
	    							biaozhi[j][j+i+1].setmin(tempmin,j,j+meici-1,j+meici,j+i+1);
	    					}
	                  
	    			}
	    tempmax=-Double.MAX_VALUE;
	    tempmin=Double.MAX_VALUE;
	          }
	                }
	    chulijieguo();
System.out.printf("hello");
	}
	
}
