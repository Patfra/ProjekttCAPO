package pl.edu.agh.capo;
/**
 * Created by Ryœ on 2015-04-09.
 */

import pl.edu.agh.amber.hokuyo.*;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.roboclaw.MotorsCurrentSpeed;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;
import pl.edu.agh.capo.Keyboard.KeyboardSteering;

public class RunCapoRun {

    public static void main(String[] args) throws Exception {
        //


        AmberClient client = new AmberClient("192.168.2.203", 26233);
        RoboclawProxy roboclawProxy = new RoboclawProxy(client, 0);
        SingleScan scann=new SingleScan(client, roboclawProxy, 0);
        MotorsCurrentSpeed motorsCurrentSpeed=new MotorsCurrentSpeed(0,0,0,0);

        //scann.printScan();
        KeyboardSteering keyboardSteering=new KeyboardSteering(client, roboclawProxy, motorsCurrentSpeed, 0);
        Thread thread=new Thread(keyboardSteering);
        thread.start();
        Thread.sleep(10000);
        client.terminate();


    }

}
