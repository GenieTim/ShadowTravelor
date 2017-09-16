package bernhardwebstudio.shadowtravelor.apidata;

import android.annotation.TargetApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.GregorianCalendar;

/**
 * Created by Benedict on 16.09.2017.
 */

public class SBB {

    public SBB() {

    }

    public InputStream getPassangerAmount(String station) throws IOException {
        String url = "data.sbb.ch/";
        if(station != null){
            String query = "api/records/1.0/search/?dataset=passenger-frequence&q="+station;

            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.setDoOutput(true); // Triggers POST.
            urlConnection.setRequestProperty("accept-charset", charset);
            urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            OutputStreamWriter writer = null;
            try {
                writer = new OutputStreamWriter(urlConnection.getOutputStream(), charset);
                writer.write(query); // Write POST query string (if any needed).
            } finally {
                if (writer != null) try {
                    writer.close();
                } catch (IOException logOrIgnore) {
                }
            }

            return urlConnection.getInputStream();
        }
        return null;
    }

    public void getStationNumber(String name) {

    }

    @TargetApi(19)
    public void getDepartures(String station) {
        InputStream is = null;
        try {
            is = httprequest("departure", station, "");

        } catch (Exception e) {

        }
    }

    public void getArrivals(String station) {
        InputStream is = null;
        try {
            is = httprequest("arrival", station, "");

        } catch (Exception e) {

        }
    }


    public void getConnection(String start, String stop, GregorianCalendar datetime) {
        this.getConnection(start, stop, datetime, false);
    }

    // TODO!
    public void getConnection(String start, String stop, GregorianCalendar datetime, boolean arrival) {
        String station = start + ", " + stop;
        try {
            if (arrival) {
                httprequest("arrival", station, datetime.toString());
            } else {
                httprequest("departure", station, datetime.toString())
            }
        } catch(IOException e){

        }
    }

    private InputStream httprequest(String type, String station, String timestamp) throws IOException {
        String url = "https://api.opentransportdata.swiss/trias";
        String charset = "UTF-8";
        String param1 = URLEncoder.encode("param1", charset);
        String param2 = URLEncoder.encode("param2", charset);
        String query = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Trias version=\"1.1\" xmlns=\"http://www.vdv.de/trias\" xmlns:siri=\"http://www.siri.org.uk/siri\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <ServiceRequest>\n" +
                "        <siri:RequestTimestamp>%s</siri:RequestTimestamp>\n" +
                "        <siri:RequestorRef>EPSa</siri:RequestorRef>\n" +
                "        <RequestPayload>\n" +
                "            <StopEventRequest>\n" +
                "                <Location>\n" +
                "                    <LocationRef>\n" +
                "                        <StopPointRef>%s</StopPointRef>\n" +
                "                    </LocationRef>\n" +
                "                    <DepArrTime>%s</DepArrTime>\n" +
                "                </Location>\n" +
                "                <Params>\n" +
                "                    <NumberOfResults></NumberOfResults>\n" +
                "                    <StopEventType>%s</StopEventType>\n" +
                "                    <IncludePreviousCalls>true</IncludePreviousCalls>\n" +
                "                    <IncludeOnwardCalls>true</IncludeOnwardCalls>\n" +
                "                    <IncludeRealtimeData>true</IncludeRealtimeData>\n" +
                "                </Params>\n" +
                "            </StopEventRequest>\n" +
                "        </RequestPayload>\n" +
                "    </ServiceRequest>\n" +
                "</Trias>", station, timestamp, type);

        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.setUseCaches(false);
        urlConnection.setDoOutput(true); // Triggers POST.
        urlConnection.setRequestProperty("accept-charset", charset);
        urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");

        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(urlConnection.getOutputStream(), charset);
            writer.write(query); // Write POST query string (if any needed).
        } finally {
            if (writer != null) try {
                writer.close();
            } catch (IOException logOrIgnore) {
            }
        }

        return urlConnection.getInputStream();
    }

}
