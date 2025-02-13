package main

import (
	"encoding/json"
	"encoding/xml"
	"fmt"
	"log"
	"net"
	"strings"
)

const (
	SERVER_HOST  = "10.142.0.2"
	SERVER_PORT  = "8932"
	SERVER_TYPE  = "tcp"
	BUFFER_SIZE  = 2048
	STUDENT_NAME = "Upi"
	STUDENT_NPM  = "2206028932"
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
	l, err := net.Listen("tcp", fmt.Sprintf("%s:%s", SERVER_HOST, SERVER_PORT)); if err != nil {log.Fatal(err)}
	defer l.Close()

	fmt.Println("Server Listening on " + SERVER_HOST + ":" + SERVER_PORT)
	fmt.Println("Waiting for client...")

	for {
		conn, err := l.Accept(); if err != nil {log.Fatal(err)}
		fmt.Println("Client accepted")
		go HandleConnection(conn)
	}
}

func HandleConnection(connection net.Conn) {
	defer connection.Close()

	data := make([]byte, BUFFER_SIZE)
	_, err := connection.Read(data); if err != nil {log.Fatal(err)}

	connection.Write(
		ResponseEncoder(
			HandleRequest(
				RequestDecoder(data),
			),
		),
	)
}

func HandleRequest(req HttpRequest) HttpResponse {
	if req.Uri == "/" {
		return index(req)
	} else if req.Uri == "/greet/"+STUDENT_NPM || strings.HasPrefix(req.Uri, "/greet/"+STUDENT_NPM+"?name=") {
		return greet(req)
	} else {
		return notFound(req)
	}
}

func RequestDecoder(bytestream []byte) HttpRequest {
	data := strings.Split(string(bytestream), "\r\n")
	methodUriVersion := strings.Split(data[0], " ")

	dict := make(map[string]string)
	for _, v := range data[1:] {
		header := strings.Split(v, ": ")
		dict[header[0]] = strings.Join(header[1:], " ")
	}

	return HttpRequest{
		Method:  methodUriVersion[0],
		Uri:     methodUriVersion[1],
		Version: methodUriVersion[2],
		Host:    dict["Host"],
		Accept:  dict["Accept"],
	}
}

func ResponseEncoder(res HttpResponse) []byte {
	return []byte(fmt.Sprintf(
			"%s %s\r\n" +
			"Content-Type: %s\r\n\r\n" +
			"%s",
			res.Version,
			res.StatusCode,
			res.ContentType,
			res.Data,
		),
	)
}

func index(_ HttpRequest) HttpResponse {
	return HttpResponse{
		StatusCode:  "200",
		Version:     "HTTP/1.1",
		ContentType: "text/html",
		Data: fmt.Sprintf(
			"<html><body><h1>Halo, dunia! Aku %s</h1></body></html>",
			STUDENT_NAME,
		),
	}
}

func notFound(_ HttpRequest) HttpResponse {
	return HttpResponse{
		StatusCode: "404",
		Version:    "HTTP/1.1",
	}
}

func greet(req HttpRequest) HttpResponse {
	greeter := STUDENT_NAME

	if strings.HasPrefix(req.Uri, "/greet/"+STUDENT_NPM+"?name=") {
		greeter = strings.Split(req.Uri, "?name=")[1]
	}

	response := GreetResponse{
		Student: Student{
			Nama: STUDENT_NAME,
			Npm:  STUDENT_NPM,
		},
		Greeter: greeter,
	}

	if req.Accept == "application/xml" {
		data, err := xml.Marshal(response); if err != nil {log.Fatal(err)}

		cType := "application/xml"

		return HttpResponse{
			StatusCode:  "200",
			Version:     "HTTP/1.1",
			ContentType: cType,
			Data:        string(data),
		}
	} else {
		data, err := json.Marshal(response); if err != nil {log.Fatal(err)}

		cType := "application/json"

		return HttpResponse{
			StatusCode:  "200",
			Version:     "HTTP/1.1",
			ContentType: cType,
			Data:        string(data),
		}
	}
}
