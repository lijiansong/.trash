#include<cv.h>
#include<highgui.h>
#include<iostream>
int main(int argc,char* argv[])
{
    CvMat* matrix_data=cvCreateMat(5,5,CV_32FC1);
    float element_3_2 = 7.7;
    *((float*)CV_MAT_ELEM_PTR( *matrix_data, 3,2) ) = element_3_2;
    cvmSet(matrix_data,4,4,0.5000);
    cvSetReal2D(matrix_data,3,3,0.5000);

    CvMat A=cvMat(5,5,CV_32F,matrix_data);
    cvSave("my_matrix.xml",&A);
    std::cout<<"loading my_matrix.xml"<<std::endl;
    CvMat* A1 = (CvMat*) cvLoad( "my_matrix.xml" );
    return 0;
}
