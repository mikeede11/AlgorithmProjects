package edu.yu.introtoalgs;


public class EstimateSecretAlgorithmsClient {

    public static void main(String[] args){
        SecretAlgorithm1 test1 = new SecretAlgorithm1();
        SecretAlgorithm2 test2 = new SecretAlgorithm2();
        SecretAlgorithm3 test3 = new SecretAlgorithm3();
        SecretAlgorithm4 test4 = new SecretAlgorithm4();
        BigOMeasurable[] secretAlgs = {test1, test2, test3, test4};
        for(BigOMeasurable algorithm: secretAlgs) {//iterate through each secretAlgorithm analyzing their execution time in relation to input change
            System.out.println("Big O Analysis for: " + algorithm.getClass());
            //125 will be our starting n size and then we will use a loop to double input size repeatedly - this is similar to Sedgewick's model
            algorithm.setup(125);
            long startTime0 = System.currentTimeMillis();
            algorithm.execute();
            long endTime0 = System.currentTimeMillis();
            double prev = endTime0 - startTime0;
            System.out.printf("Input Size: %10d Running Time: %10.2f (ms) Doubling Ratio: Infinity\n", 125, prev);
            for (int n = 250; n < 100000000; n *= 2) {//time each trial, each trial has double the input of last
                algorithm.setup(n);
                long startTime = System.currentTimeMillis();
                algorithm.execute();
                long endTime = System.currentTimeMillis();
                double time = endTime - startTime;
                if(time >= 5)//if the time is less than 5 its not worth plotting it because the ratios will be unrecognizable so close to zero
                    System.out.printf("Input Size: %10d Running Time: %10.2f (ms) Doubling Ratio: %4.2f\n", n, time, time / prev);
                prev = time;
                if(prev > 100000)break;//if time is over 100 seconds stop the trials we should have a good idea by now
            }
        }
    }
}
