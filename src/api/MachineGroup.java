/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This interface provides for separation between infrastructure and client software. Implementing
 * classes should specify the return type of the remote object.
 * @author charlesmunger
 */
public abstract class MachineGroup<T>
{
    private ExecutorService exec = Executors.newCachedThreadPool();
    public abstract String getHostname();
    public Future<T> deploy(String... args) throws IOException {
        return exec.submit(syncDeploy(args));
    }
    public abstract void reset() throws IOException;
    public abstract void terminate() throws IOException;
    public abstract Callable<T> syncDeploy(String... args);
}
