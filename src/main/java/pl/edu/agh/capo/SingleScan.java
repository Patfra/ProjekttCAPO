package pl.edu.agh.capo;
/**
 * Created by Ryœ on 2015-04-18.
 */
import pl.edu.agh.amber.hokuyo.*;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.MotorsCurrentSpeed;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;

public class SingleScan {
        AmberClient amberClient;
        HokuyoProxy hokuyoProxy;
        RoboclawProxy roboclawProxy;
        private final int id;
        private Scan scan;


        public SingleScan(AmberClient amberClient,RoboclawProxy roboclawProxy, int id)
        {
            this.amberClient = amberClient;
            this.roboclawProxy = roboclawProxy;
            this.id = id;
            hokuyoProxy=new HokuyoProxy(amberClient, id);
        }
        public void printScan() throws java.lang.Exception{
            for (int i = 0; i < 100; i++) {
                scan = hokuyoProxy.getSingleScan();
                for (MapPoint mapPoint : scan.getPoints()) {
                    System.out.println(mapPoint.toString());
                    if (mapPoint.getDistance() > 300)
                        roboclawProxy.sendMotorsCommand(i * 10, i * 10, i * 10, i * 10);
                }
                System.out.println();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
