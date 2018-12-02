package jdk.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class AttachTest {

    public static void main(String[] args) throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException {
        if (args.length <= 1) {
            System.out.println("Usage: java AttachTest <PID> /PATH/TO/AGENT.jar");
            return;
        }
        VirtualMachine attach = VirtualMachine.attach(args[0]);
        attach.loadAgent(args[1]);

    }
}
