library("e1071")
x<-read.table("/home/admin1/Desktop/ML_project/Machine_Learning_Project/data.txt",sep=',')
model<-naiveBayes(x[-142],x[,142])
y<-read.table("/home/admin1/Desktop/ML_project/Machine_Learning_Project/test_data.txt",sep=',')
summary(predict(model,y[,1:141],type = c("class"),threshold=0.49))
