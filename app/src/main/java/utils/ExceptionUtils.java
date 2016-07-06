package utils;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;

/**异常处理工具类    处理全局异常
 * Created by Administrator on 2016/7/5.
 */
public class ExceptionUtils implements Thread.UncaughtExceptionHandler{
    //---------------单例模式获取对象
    private Context context;
    private ExceptionUtils(){}
    private static ExceptionUtils instance;
    public static ExceptionUtils getInstance(){
        if(instance==null){
            synchronized (ExceptionUtils.class){
                instance=new ExceptionUtils();
            }
        }
        return instance;
    }

    private Thread.UncaughtExceptionHandler carshHandler;

    /**
     * 初始化
     * @param context
     * */
    public void init(Context context){
        this.context=context;
        Thread.setDefaultUncaughtExceptionHandler(this);
        carshHandler=Thread.getDefaultUncaughtExceptionHandler();
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        boolean isHandler=handleException(ex);
        if (carshHandler!=null&&!isHandler)
            carshHandler.uncaughtException(thread,ex);
        else {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 异常处理方法
     * @param ex
     * */
    private boolean handleException(final Throwable ex){
        if (ex==null){
            return false;
        }else {
            //开启新线程在UI上显示提示消息
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Looper.prepare();
                    Toast.makeText(context,"亲，app要爆炸了，赶紧撤退",Toast.LENGTH_LONG).show();

                    Looper.loop();
                }
            }.start();
            Log.e("xv","has handle the exception"+getErrorInfo(ex));

            return true;
        }

    }


    /**
     * 获取错误的信息
     * @param ex
     * @return
     */
    private String getErrorInfo(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        pw.close();
        String error= writer.toString();
        return error;
    }
}
