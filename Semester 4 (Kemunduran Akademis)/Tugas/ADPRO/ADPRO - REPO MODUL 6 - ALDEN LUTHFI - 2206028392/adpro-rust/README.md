# Tutorial Pemrograman Lanjut
## Alden Luthfi - 2206028932

### Refleksi Commit 1
In this commit I wrote (or rather copied) my first rust code. It looks familiar, we have the semocolons, curly-brackets for-loops, import statements. But there are some new things that makes it somewhat unique. Since I mostly copied the code in the module, understanding it might be later in the agenda. The rudimentary web server has a very familiar setup as well

### Refleksi Commit 2
![Commit 2 screen capture](/assets/images/commit2.jpeg)

In this commit, I added a simple landing page to the webserver. The landing page is a simple HTML page with a title and a paragraph. The page is served at the root of the server. The code is simple and easy to understand. The code is also well commented, making it easy to understand what each part of the code does. But I dont like how there will be a bunch of html files just for the sake of serving a page.

### Refleksi Commit 3
![Commit 3 screen capture](/assets/images/commit3.jpeg)
Followed the link given on how to route for unknown pages. Pretty standard procedure for a 404 page. The book is easy to follow. I'm starting to get a feel of the syntax and feel of rust. The code is simple and easy to understand, making it easy to follow the flow of the code. there are also some refactoring happening.

### Refleksi Commit 4
There is little to reflect here. Just copy pasting another sample of code. Did the thing where I open 2 browser windows and indeed it was slow. It's pretty obvious it has something to do with single threading. In the modified handle_connection function, the reading of HTTP request data from the client is done differently. Now, only the first line of the request is read using buf_reader.lines().next().unwrap().unwrap(), which stores it in the variable request_line. Based on the content of this request line, it is used to determine the status line and the file name to be sent as a response. If the request line matches "GET / HTTP/1.1", then the status line is set to "HTTP/1.1 200 OK" and the file name becomes "index.html". However, if it doesn't match, then the status line is set to "HTTP/1.1 404 NOT FOUND" and the file name becomes "404.html". Next, the content of the file corresponding to the filename is read using fs::read_to_string(filename) and stored in the variable contents. The length of this content is calculated for the Content-Length header. Finally, an HTTP response is constructed according to the appropriate HTTP protocol format, including the status line, Content-Length header, and content. This response is then sent back to the client over the TCP connection using stream.write_all().

### Refleksi Commit 5
A thread pool is a group of pre-instantiated, idle threads which stand ready to be given work. These are preferred over instantiating new threads for each task when there is a large number of short tasks to be done rather than a small number of long ones. This prevents having to incur the overhead of creating a thread a large number of times. There is also little to be reported here. Following the book is easy and the code is managable to follow

