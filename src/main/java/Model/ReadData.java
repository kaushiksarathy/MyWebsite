package Model;

import Model.Bean.Blog;
import Model.Bean.CompleteBlog;
import Model.DAO.BlogDAO;
import Model.DAO.ExternalUrlDAO;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.gdata.client.spreadsheet.FeedURLFactory;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.*;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.util.ServiceException;



import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReadData {


//    public static final String SPREADSHEET_URL ="https://spreadsheets.google.com/feeds/spreadsheets/10Rv0Kd6iBp_a5q7ozfb24LOZJ0Z4jsRcq-H63Pwpgfg/private/full";//https://docs.google.com/spreadsheets/d/10Rv0Kd6iBp_a5q7ozfb24LOZJ0Z4jsRcq-H63Pwpgfg/edit#gid=0";
    public static final String SPREADSHEET_URL ="https://spreadsheets.google.com/feeds/worksheets/10Rv0Kd6iBp_a5q7ozfb24LOZJ0Z4jsRcq-H63Pwpgfg/private/full";//https://docs.google.com/spreadsheets/d/10Rv0Kd6iBp_a5q7ozfb24LOZJ0Z4jsRcq-H63Pwpgfg/edit#gid=0";


    public List<CompleteBlog> getData()throws MalformedURLException, GeneralSecurityException, IOException, ServiceException{
        List<CompleteBlog> completeBlogList=new ArrayList<>();
        String status="";
        System.out.println("key");
        java.io.File p12 = new java.io.File("src/main/resources/weBlog-2d92ff2bcb8b.p12");
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        String [] SCOPESArray= {"https://spreadsheets.google.com/feeds", "https://docs.google.com/feeds"};
        final List SCOPES = Arrays.asList(SCOPESArray);
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId("kaushik@weblog-1158.iam.gserviceaccount.com")
                .setServiceAccountScopes(SCOPES)
                .setServiceAccountPrivateKeyFromP12File(p12)
                .build();




        try{
            /** Our view of Google Spreadsheets as an authenticated Google user. */
            SpreadsheetService service = new SpreadsheetService("Print Google Spreadsheet Demo");
            service.setOAuth2Credentials(credential);
            URL metafeedUrl = new URL(SPREADSHEET_URL);
            SpreadsheetFeed feed = service.getFeed(metafeedUrl, SpreadsheetFeed.class);


            List<SpreadsheetEntry> spreadsheets = feed.getEntries();


            if (spreadsheets.size() == 0) {
                // TODO: There were no spreadsheets, act accordingly.
            }

            FeedURLFactory urlFactory = FeedURLFactory.getDefault();
            WorksheetFeed worksheetFeed = service.getFeed(urlFactory.getWorksheetFeedUrl("10Rv0Kd6iBp_a5q7ozfb24LOZJ0Z4jsRcq-H63Pwpgfg", "private", "full"), WorksheetFeed.class);
            List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
            WorksheetEntry worksheet = worksheets.get(0);

            URL url=worksheet.getListFeedUrl();
            Iterator<ListEntry> listEntryIterator = service.getFeed(url, ListFeed.class)
                            .getEntries().iterator();
                    while (listEntryIterator.hasNext()) {
                        String title=null;
                        String description=null;
                        String genre=null;
                        String [] tags=null;
                        Timestamp publishedOn=null;
                        List<String> urls=new ArrayList<>();

                        ListEntry listEntry = (ListEntry) listEntryIterator.next();
                        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

                        for (String tag : listEntry.getCustomElements().getTags()) {
                            System.out.println("     " + tag + ": "
                                    + listEntry.getCustomElements().getValue(tag));
                            status = listEntry.getCustomElements().getValue(tag);
                            if(tag.equalsIgnoreCase("title"))
                                title=listEntry.getCustomElements().getValue(tag);
                            else if(tag.equalsIgnoreCase("description"))
                                description=listEntry.getCustomElements().getValue(tag);
                            else if(tag.equalsIgnoreCase("genre"))
                                genre=listEntry.getCustomElements().getValue(tag);
                            else if(tag.equalsIgnoreCase("publishedon"))
                                publishedOn=new Timestamp(Long.valueOf(listEntry.getCustomElements().getValue(tag)));
                            else if(tag.equalsIgnoreCase("tag"))
                                tags=listEntry.getCustomElements().getValue(tag).split(",");
                            else if(tag.equalsIgnoreCase("urls"))
                                urls=Arrays.asList(listEntry.getCustomElements().getValue(tag).split(","));


                            System.out.println("reading data and filling");
                        }
                        System.out.println(title+description+genre+publishedOn);
                        completeBlogList.add(new CompleteBlog(UUID.randomUUID().toString().replace("-",""),title, genre,description,publishedOn,urls));

                    }




        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("size of list "+completeBlogList.size());

        return completeBlogList;

    }

    public static void main(String[] args) throws ServiceException, IOException, GeneralSecurityException {
        ReadData readData=new ReadData();
        readData.getData();
    }
}