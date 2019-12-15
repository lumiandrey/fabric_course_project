package connection.client_support.requestbody;

import org.jetbrains.annotations.NotNull;
import connection.enumer.MediaTypeRequestResponce;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class StringRequestBody extends RequestBody {

    private final String senderString;

    public StringRequestBody(@NotNull final String senderString) {
        this.senderString = senderString;
    }

    @Override
    void writeTo(PrintStream outputStream) throws IOException {

        //для записи потока байт
        PrintStream sout = new PrintStream(new BufferedOutputStream(outputStream), true);

        sout.println(senderString);
    }

    @Override
    public MediaTypeRequestResponce contentType() {
        return MediaTypeRequestResponce.STRING;
    }

    @Override
    public long contentLength() {
        return senderString.length();
    }
}
