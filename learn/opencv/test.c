#include<highgui.h>
int main(int argc,char** argv)
{
    IplImage* img=cvLoadImage(argv[1],1);
    cvNamedWindow("Test",CV_WINDOW_AUTOSIZE);
    cvShowImage("Test",img);
    cvWaitKey(0);
    cvReleaseImage(&img);
    cvDestroyWindow("Test");
    return 0;
}
