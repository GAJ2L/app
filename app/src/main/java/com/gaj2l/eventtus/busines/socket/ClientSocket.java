package com.gaj2l.eventtus.busines.socket;

import android.os.StrictMode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by lucas on 07/05/17.
 */

public abstract class ClientSocket
        extends
        Thread
{
    private static final String  HOST = "45.55.153.203";
    private static final Integer PORT = 443;
    private static Socket  socket;
    private static DataOutputStream out;
    private static DataInputStream in;

    public ClientSocket()
    {
        try {
            socket = new Socket( HOST,PORT );
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDaemon(true);
    }

    public void send( String source ) throws Exception
    {
        if ( source != null && out != null )
        {
                out.writeUTF(source);
        }
    }

    public abstract void onRecive( String  data ) throws Exception;

    @Override
    public void run()
    {
        try
        {
            out    = new DataOutputStream(socket.getOutputStream());
            in     = new DataInputStream(socket.getInputStream());
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        try
        {
            while ( true )
            {
                Thread.sleep(3000);
                byte[] b = new byte[8];
                in.read(b);
                String retorno = new String(b, "UTF-8").substring(0,7);
                if( retorno.equalsIgnoreCase("recived") )
                {
                    onRecive( retorno );
                }
            }
        }

        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }


    public static boolean isRunning() {
        try
        {
            if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Socket s = new Socket(HOST, PORT);
            new DataOutputStream(s.getOutputStream()).writeUTF("running?");
            s.close();
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}