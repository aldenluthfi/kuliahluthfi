package main

import (
	"bufio"
	"encoding/json"
	"encoding/xml"
	"fmt"
	"log"
	"net"
	"net/url"
	"os"
	"strings"
)

const (
	SERVER_TYPE = "tcp"
	BUFFER_SIZE = 2048
)


type Student struct {
	Nama string
	Npm  string
}

type GreetResponse struct {
	Student Student
	Greeter string
}

type HttpRequest struct {
	Method  string
	Uri     string
	Version string
	Host    string
	Accept  string
}

type HttpResponse struct {
	Version       string
	StatusCode    string
	ContentType   string
	ContentLength int
	Data          string
}

func main() {
	reader := bufio.NewReader(os.Stdin)

	fmt.Print("input the url: ")
	url_, err := reader.ReadString('\n'); if err != nil {log.Fatal(err)}
	u, err := url.Parse(strings.TrimSpace(url_)); if err != nil {log.Fatal(err)}

	fmt.Print("input the data type: ")
	datatype, err := reader.ReadString('\n'); if err != nil {log.Fatal(err)}

	req := HttpRequest{
		Uri: u.RequestURI(),
		Host: u.Host,
		Accept: strings.TrimRight(datatype, "\r\n"),
		Method: "GET",
	}

	connection, err := net.Dial(SERVER_TYPE, req.Host); if err != nil {log.Fatal(err)}
	defer connection.Close()

	res := Fetch(req, connection)
	fmt.Printf("Status Code: %s \n", res.StatusCode)
	fmt.Printf("Body: %s \n", res.Data)

	if !strings.Contains(datatype, "html") {
		var resp GreetResponse

		if res.ContentType == "application/json" {
			json.Unmarshal([]byte(res.Data), &resp)
		} else {
			xml.Unmarshal([]byte(res.Data), &resp)
		}

		fmt.Printf("Parsed: %s \n", resp)
	}
}

func Fetch(req HttpRequest, connection net.Conn) HttpResponse {

	connection.Write(
		RequestEncoder(req),
	)

	buffer := make([]byte, BUFFER_SIZE)
	len, err := connection.Read(buffer)

	if nil != err {
		fmt.Println("Error reading:", err.Error())
	}

	return ResponseDecoder(buffer[:len])
}

func ResponseDecoder(bytestream []byte) HttpResponse {

	data := strings.Split(string(bytestream), "\r\n")
	versionStatus := strings.Split(data[0], " ")

	dict := make(map[string]string)
	for _, v := range data[1:] {
		if !strings.Contains(v, ": ") {
			break
		}

		header := strings.Split(v, ": ")
		dict[header[0]] = header[1]
	}

	return HttpResponse{
		Version: versionStatus[0],
		StatusCode: versionStatus[1],
		ContentType: dict["Content-Type"],
		Data: strings.Replace(data[len(data)-1], "\n", "", -1),
	}
}

func RequestEncoder(req HttpRequest) []byte {
	return []byte(fmt.Sprintf(
		"%s %s %s\r\n" +
		"Host: %s\r\n" +
		"Accept: %s\r\n\r\n",
		req.Method, req.Uri, req.Version, req.Host, req.Accept,
		),
	)
}
