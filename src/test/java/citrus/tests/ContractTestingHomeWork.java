package citrus.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;
import com.consol.citrus.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

public class ContractTestingHomeWork extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient restClient;
    private HttpServer restServer;

    private TestContext context;

    @Test(description = "Получение списка пользователей на 2 странице")
    @CitrusTest
    public void listUsers() {
        this.context = citrus.createTestContext();


        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("users?page=2")
        );


        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"page\": 2,\n" +
                        "    \"per_page\": 6,\n" +
                        "    \"total\": 12,\n" +
                        "    \"total_pages\": 2,\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"id\": 7,\n" +
                        "            \"email\": \"michael.lawson@reqres.in\",\n" +
                        "            \"first_name\": \"Michael\",\n" +
                        "            \"last_name\": \"Lawson\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 8,\n" +
                        "            \"email\": \"lindsay.ferguson@reqres.in\",\n" +
                        "            \"first_name\": \"Lindsay\",\n" +
                        "            \"last_name\": \"Ferguson\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/8-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 9,\n" +
                        "            \"email\": \"tobias.funke@reqres.in\",\n" +
                        "            \"first_name\": \"Tobias\",\n" +
                        "            \"last_name\": \"Funke\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/9-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 10,\n" +
                        "            \"email\": \"byron.fields@reqres.in\",\n" +
                        "            \"first_name\": \"Byron\",\n" +
                        "            \"last_name\": \"Fields\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/10-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 11,\n" +
                        "            \"email\": \"george.edwards@reqres.in\",\n" +
                        "            \"first_name\": \"George\",\n" +
                        "            \"last_name\": \"Edwards\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/11-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 12,\n" +
                        "            \"email\": \"rachel.howell@reqres.in\",\n" +
                        "            \"first_name\": \"Rachel\",\n" +
                        "            \"last_name\": \"Howell\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/12-image.jpg\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"support\": {\n" +
                        "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                        "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                        "    }\n" +
                        "}")
        );

    }

    @Test(description = "Получение ошибки, если пользователь не найден")
    @CitrusTest
    public void userNotFound() {
        this.context = citrus.createTestContext();


        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("users/23")
        );


        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response(HttpStatus.valueOf(404))
                .messageType(MessageType.JSON)
                .payload("{}")
        );
    }

    @Test(description = "Получение списка ресурсов")
    @CitrusTest
    public void listResource() {
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("unknown")
        );


        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"page\": 1,\n" +
                        "    \"per_page\": 6,\n" +
                        "    \"total\": 12,\n" +
                        "    \"total_pages\": 2,\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"id\": 1,\n" +
                        "            \"name\": \"cerulean\",\n" +
                        "            \"year\": 2000,\n" +
                        "            \"color\": \"#98B2D1\",\n" +
                        "            \"pantone_value\": \"15-4020\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 2,\n" +
                        "            \"name\": \"fuchsia rose\",\n" +
                        "            \"year\": 2001,\n" +
                        "            \"color\": \"#C74375\",\n" +
                        "            \"pantone_value\": \"17-2031\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 3,\n" +
                        "            \"name\": \"true red\",\n" +
                        "            \"year\": 2002,\n" +
                        "            \"color\": \"#BF1932\",\n" +
                        "            \"pantone_value\": \"19-1664\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 4,\n" +
                        "            \"name\": \"aqua sky\",\n" +
                        "            \"year\": 2003,\n" +
                        "            \"color\": \"#7BC4C4\",\n" +
                        "            \"pantone_value\": \"14-4811\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 5,\n" +
                        "            \"name\": \"tigerlily\",\n" +
                        "            \"year\": 2004,\n" +
                        "            \"color\": \"#E2583E\",\n" +
                        "            \"pantone_value\": \"17-1456\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 6,\n" +
                        "            \"name\": \"blue turquoise\",\n" +
                        "            \"year\": 2005,\n" +
                        "            \"color\": \"#53B0AE\",\n" +
                        "            \"pantone_value\": \"15-5217\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"support\": {\n" +
                        "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                        "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                        "    }\n" +
                        "}")
        );


    }



    @Test(description = "Удаление пользователя")
    @CitrusTest
    public void deleteUser() {
        this.context = citrus.createTestContext();

            http(httpActionBuilder -> httpActionBuilder
                    .client(restClient)
                    .send()
                    .delete("users/2")
            );

            http(httpActionBuilder -> httpActionBuilder
                    .client(restClient)
                    .receive()
                    .response(HttpStatus.valueOf(204))
                    .messageType(MessageType.JSON)
            );
        }

    @Test(description = "Создание пользователя")
    @CitrusTest
    public void сreateUser(){
        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .post("users")
                .payload("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response(HttpStatus.valueOf(201))
                .messageType(MessageType.JSON)
        );
    }

}

