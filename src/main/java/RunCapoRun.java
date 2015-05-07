/**
 * Created by Ryœ on 2015-04-09.
 */
package pl.edu.agh.capo;
import capo.AntiCrashAlgorithm;
import pl.edu.agh.amber.hokuyo.*;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.MotorsCurrentSpeed;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

public class RunCapoRun {

    public static void main(String[] args) throws Exception {
        //


        AmberClient client = new AmberClient("192.168.2.203", 26233);
        RoboclawProxy roboclawProxy = new RoboclawProxy(client, 0);
        HokuyoProxy hokuyoProxy = new HokuyoProxy(client, 0);
        Scan scan = new Scan();
        System.out.println(String.format("Getting Scan"));
        MotorsCurrentSpeed mcs;
        AntiCrashAlgorithm Run = new AntiCrashAlgorithm(roboclawProxy, hokuyoProxy,scan);
//        roboclawProxy.sendMotorsCommand(100,0,0,0);
//        Thread.sleep(5000);
//        roboclawProxy.sendMotorsCommand(0,0,0,0);
        scan=hokuyoProxy.getSingleScan();
        for(MapPoint p: scan.getPoints())
            System.out.println(p.toString());

        Run.AutonomicRide(10);

        /*for (int i = 1; i <= 10; i++) {
            roboclawProxy.sendMotorsCommand(100 * i, 100 * i, 100 * i, 100 * i);

            Thread.sleep(500);

            mcs = roboclawProxy.getCurrentMotorsSpeed();
            mcs.waitAvailable();
            System.out.println(String.format("Motors current speed: fl: %d, fr: %d, rl: %d, rr: %d",
                    mcs.getFrontLeftSpeed(), mcs.getFrontRightSpeed(), mcs.getRearLeftSpeed(), mcs.getRearRightSpeed()));

        }*/



        Thread.sleep(2000);

        roboclawProxy.sendMotorsCommand(0, 0, 0, 0);
        client.terminate();


    }

}
