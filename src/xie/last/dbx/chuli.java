package xie.last.dbx;

public class chuli {

	int flagmax;
	int flagmin;
	double maxshu;
	int maxleftx;
	int maxlefty;
	int maxrightx;
	int maxrighty;
	
	double minshu;
	int minleftx;
	int minlefty;
	int minrightx;
	int minrighty;

	void setmax(double shu1,int leftx1,int lefty1,int rightx1,int righty1)
	{
		maxshu=shu1;
		maxleftx=leftx1;
		maxlefty=lefty1;
		maxrightx=rightx1;
		maxrighty=righty1;
		}
	void setmin(double shu1,int leftx1,int lefty1,int rightx1,int righty1)
	{
		minshu=shu1;
		minleftx=leftx1;
		minlefty=lefty1;
		minrightx=rightx1;
		minrighty=righty1;
		}
}
