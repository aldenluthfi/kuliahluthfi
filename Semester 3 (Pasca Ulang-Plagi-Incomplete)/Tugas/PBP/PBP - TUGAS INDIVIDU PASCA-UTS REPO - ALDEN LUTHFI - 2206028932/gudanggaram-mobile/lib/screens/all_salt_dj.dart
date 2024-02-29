import 'package:flutter/material.dart';
import 'package:gudang_garam/screens/salt_detail.dart';
import 'package:gudang_garam/models/salt.dart';
import 'package:gudang_garam/widgets/drawer.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class ItemPage extends StatefulWidget {
  const ItemPage({Key? key}) : super(key: key);

  @override
  _ItemPageState createState() => _ItemPageState();
}

class _ItemPageState extends State<ItemPage> {


  Future<List<Salts>> fetchItem() async {
    var url = Uri.parse('https://alden-luthfi-tugas.pbp.cs.ui.ac.id/debug/json/');
    var response = await http.get(
      url,
      headers: {"Content-Type": "application/json"},
    );

    var data = jsonDecode(utf8.decode(response.bodyBytes));

    List<Salts> listItem = [];
    for (var d in data) {
      if (d != null) {
        listItem.add(Salts.fromJson(d));
      }
    }
    return listItem;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('List Item'),
          backgroundColor: const Color(0xFF419197),
        foregroundColor: Colors.white,
        ),
        drawer: const DrawerNavi(),
        body: FutureBuilder(
          future: fetchItem(),
          builder: (context, AsyncSnapshot snapshot) {
            if (snapshot.data == null) {
              return const Center(child: CircularProgressIndicator());
            } else {
              if (!snapshot.hasData) {
                return const Column(
                  children: [
                    Text(
                      "Tidak ada data item.",
                      style:
                          TextStyle(color: Color(0xff59A5D8), fontSize: 20),
                    ),
                    SizedBox(height: 8),
                  ],
                );
              } else {
                return ListView.builder(
                  itemCount: snapshot.data!.length,
                  itemBuilder: (_, index) => Container(
                    margin: const EdgeInsets.symmetric(
                        horizontal: 16, vertical: 12),
                    padding: const EdgeInsets.all(20.0),
                    child: ClipRRect(
                      borderRadius: BorderRadius.circular(20.0),
                      child: Material(
                        color: const Color(0xFF78D6C6),
                        child: InkWell(
                          onTap: () => Navigator.push(context, MaterialPageRoute(builder: (context)=> Detail(snapshot.data![index]))),
                          child: Column(
                            children: [
                              
                              Padding(
                                padding: const EdgeInsets.all(15.0),
                                child: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceAround,
                                  crossAxisAlignment:
                                      CrossAxisAlignment.start,
                                  children: [
                                    SizedBox(
                                      width: 200.0,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: [
                                          Text(
                                            "${snapshot.data![index].fields.name}",
                                          ),
                                        ],
                                      ),
                                    ),
                                    Column(
                                      mainAxisAlignment:
                                          MainAxisAlignment.start,
                                      children: [
                                        Text(
                                          "Amount: ${snapshot.data![index].fields.amount}",
                                        ),
                                      ],
                                    )
                                  ],
                                ),
                              ),
                              SizedBox(
                                width: 300,
                                child: Text("${snapshot.data![index].fields.description}")
                              ),
                              const SizedBox(height: 20,)
                            ],
                          ),
                        ),
                      ),
                    ),
                  )
                );
            }
          }
        }
      )
    );
  }
}
