package maow.shatter.client.ncyclo;

import maow.ncycloapi.Receiver;
import maow.shatter.client.util.StringParseUtil;

public class BeginReceiver implements Receiver {
    @Override
    public void onReceive(String data) {
        StringParseUtil.parseBegin(data);
    }
}
