package utils.appenders;

import utils.layouts.Layout;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketAppender extends AppenderImpl {
    public SocketAppender(Layout layout) {
        super(layout);
    }

    @Override
    public void append(String time, String reportLevel, String message) {
        try {
            Socket socket = new Socket("localhost", 21);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true); //auto-flash is true
            writer.write(this.getLayout().format(time, reportLevel, message));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
