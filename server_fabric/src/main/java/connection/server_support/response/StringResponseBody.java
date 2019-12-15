package connection.server_support.response;

import connection.enumer.MediaTypeRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintStream;

public class StringResponseBody extends ResponseBody {

    private final String string;

    public StringResponseBody(@NotNull final String string) {
        this.string = string;
    }

    @Override
    protected void writeTo(PrintStream outputStream) throws IOException {

        //для записи потока байт
        System.out.println("before send " + contentType().name() + " type ");

        outputStream.println(string);

        System.out.println("after send " + contentType().name() + " type ");

    }

    @Override
    public MediaTypeRequest contentType() {
        return MediaTypeRequest.STRING;
    }

    @Override
    public long contentLength() {
        return string.length();
    }
}
