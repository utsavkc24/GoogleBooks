package com.example.googlebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String JSON_STRING = "{\n" +
            "  \"kind\": \"books#volumes\",\n" +
            "  \"totalItems\": 1844,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"kind\": \"books#volume\",\n" +
            "      \"id\": \"qKFDDAAAQBAJ\",\n" +
            "      \"etag\": \"M7gycqL48/E\",\n" +
            "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/qKFDDAAAQBAJ\",\n" +
            "      \"volumeInfo\": {\n" +
            "        \"title\": \"Android\",\n" +
            "        \"authors\": [\n" +
            "          \"P.K. Dixit\"\n" +
            "        ],\n" +
            "        \"publisher\": \"Vikas Publishing House\",\n" +
            "        \"publishedDate\": \"2014\",\n" +
            "        \"description\": \"Android is a movement that has transferred data from laptop to hand-held devices like mobiles. Though there are alternate technologies that compete with Android, but it is the front runner in mobile technology by a long distance. Good knowledge in basic Java will help you to understand and develop Android technology and apps. Many universities in India and across the world are now teaching Android in their syllabus, which shows the importance of this subject. This book can be read by anyone who knows Java and XML concepts. It includes a lot of diagrams along with explanations to facilitate better understanding by students. This book aptly concludes with a project that uses Android, which will greatly benefit students in learning the practical aspects of Android. Key Features • Instructions in designing different Android user interfaces • Thorough explanations of all activities • JSON • Android-based project to aid practical understanding\",\n" +
            "        \"industryIdentifiers\": [\n" +
            "          {\n" +
            "            \"type\": \"ISBN_13\",\n" +
            "            \"identifier\": \"9789325977884\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"type\": \"ISBN_10\",\n" +
            "            \"identifier\": \"9325977885\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"readingModes\": {\n" +
            "          \"text\": false,\n" +
            "          \"image\": true\n" +
            "        },\n" +
            "        \"pageCount\": 372,\n" +
            "        \"printType\": \"BOOK\",\n" +
            "        \"categories\": [\n" +
            "          \"Computers\"\n" +
            "        ],\n" +
            "        \"averageRating\": 3,\n" +
            "        \"ratingsCount\": 1,\n" +
            "        \"maturityRating\": \"NOT_MATURE\",\n" +
            "        \"allowAnonLogging\": true,\n" +
            "        \"contentVersion\": \"preview-1.0.0\",\n" +
            "        \"panelizationSummary\": {\n" +
            "          \"containsEpubBubbles\": false,\n" +
            "          \"containsImageBubbles\": false\n" +
            "        },\n" +
            "        \"imageLinks\": {\n" +
            "          \"smallThumbnail\": \"http://books.google.com/books/content?id=qKFDDAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "          \"thumbnail\": \"http://books.google.com/books/content?id=qKFDDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "        },\n" +
            "        \"language\": \"en\",\n" +
            "        \"previewLink\": \"http://books.google.co.in/books?id=qKFDDAAAQBAJ&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api\",\n" +
            "        \"infoLink\": \"https://play.google.com/store/books/details?id=qKFDDAAAQBAJ&source=gbs_api\",\n" +
            "        \"canonicalVolumeLink\": \"https://play.google.com/store/books/details?id=qKFDDAAAQBAJ\"\n" +
            "      },\n" +
            "      \"saleInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"saleability\": \"FOR_SALE\",\n" +
            "        \"isEbook\": true,\n" +
            "        \"listPrice\": {\n" +
            "          \"amount\": 442.5,\n" +
            "          \"currencyCode\": \"INR\"\n" +
            "        },\n" +
            "        \"retailPrice\": {\n" +
            "          \"amount\": 318.25,\n" +
            "          \"currencyCode\": \"INR\"\n" +
            "        },\n" +
            "        \"buyLink\": \"https://play.google.com/store/books/details?id=qKFDDAAAQBAJ&rdid=book-qKFDDAAAQBAJ&rdot=1&source=gbs_api\",\n" +
            "        \"offers\": [\n" +
            "          {\n" +
            "            \"finskyOfferType\": 1,\n" +
            "            \"listPrice\": {\n" +
            "              \"amountInMicros\": 442500000,\n" +
            "              \"currencyCode\": \"INR\"\n" +
            "            },\n" +
            "            \"retailPrice\": {\n" +
            "              \"amountInMicros\": 318250000,\n" +
            "              \"currencyCode\": \"INR\"\n" +
            "            }\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"accessInfo\": {\n" +
            "        \"country\": \"IN\",\n" +
            "        \"viewability\": \"PARTIAL\",\n" +
            "        \"embeddable\": true,\n" +
            "        \"publicDomain\": false,\n" +
            "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "        \"epub\": {\n" +
            "          \"isAvailable\": false\n" +
            "        },\n" +
            "        \"pdf\": {\n" +
            "          \"isAvailable\": true,\n" +
            "          \"acsTokenLink\": \"http://books.google.co.in/books/download/Android-sample-pdf.acsm?id=qKFDDAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
            "        },\n" +
            "        \"webReaderLink\": \"http://play.google.com/books/reader?id=qKFDDAAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\n" +
            "        \"accessViewStatus\": \"SAMPLE\",\n" +
            "        \"quoteSharingAllowed\": false\n" +
            "      },\n" +
            "      \"searchInfo\": {\n" +
            "        \"textSnippet\": \"Many universities in India and across the world are now teaching Android in their syllabus, which shows the importance of this subject. This book can be read by anyone who knows Java and XML concepts.\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView bookTitle = (TextView)findViewById(R.id.title);
        try {
            //Create a JSONObject from the JSON_STRING
            JSONObject rootObject = new JSONObject(JSON_STRING);
            // Extract the JSONArray associated with the key called "items",
            // which represents a list of items (or books).
            JSONArray itemsArray = rootObject.getJSONArray("items");
            //Object of the first item in an array using getJSONObject
                JSONObject currentBook = itemsArray.getJSONObject(0);
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                //Get the title key.
                String title = volumeInfo.getString("title");
                bookTitle.setText(title);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
