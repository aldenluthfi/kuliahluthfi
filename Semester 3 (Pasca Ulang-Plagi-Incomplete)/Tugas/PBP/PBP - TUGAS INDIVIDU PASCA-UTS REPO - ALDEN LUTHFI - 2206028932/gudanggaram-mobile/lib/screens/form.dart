import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:gudang_garam/screens/menu.dart';
import 'package:gudang_garam/widgets/item_data.dart';
import 'package:pbp_django_auth/pbp_django_auth.dart';
import 'package:provider/provider.dart';

class AddForm extends StatefulWidget {
  const AddForm({super.key});
  @override
  State<AddForm> createState() => _AddFormState();
}

class _AddFormState extends State<AddForm> {
  final _formKey = GlobalKey<FormState>();
  String _name = "";
  int _amount = 0;
  String _description = "";

  @override
  Widget build(BuildContext context) {
    final request = context.watch<CookieRequest>();
    
    return Scaffold(
      appBar: AppBar(
        title: const Center(
          child: Text(
            'Form Simpan',
          ),
        ),
        backgroundColor: const Color(0xFF419197),
        foregroundColor: Colors.white,
      ),
      body: Form(
        key: _formKey,
        child: SingleChildScrollView(
          child: Column(
            children: [
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: TextFormField(
                  decoration: InputDecoration(
                    hintText: "Nama",
                    labelText: "Nama",
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(5.0),
                    ),
                  ),
                  onChanged: (String? value) => setState(() {
                    _name = value!;
                  }),
                  validator: (String? value) {
                    return (value == null || value.isEmpty)
                        ? "Nama tidak boleh kosong!"
                        : (int.tryParse(value) != null)
                            ? "Nama harus berupa kata!"
                            : null;
                  },
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: TextFormField(
                  decoration: InputDecoration(
                    hintText: "Amount",
                    labelText: "Amount",
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(5.0),
                    ),
                  ),
                  onChanged: (String? value) => setState(() {
                    _amount = int.parse(value!);
                  }),
                  validator: (String? value) {
                    return (value == null || value.isEmpty)
                        ? "Amount tidak boleh kosong!"
                        : (int.tryParse(value) == null)
                            ? "Amount harus berupa angka!"
                            : null;
                  },
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: TextFormField(
                  decoration: InputDecoration(
                    hintText: "Description",
                    labelText: "Description",
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(5.0),
                    ),
                  ),
                  onChanged: (String? value) {
                    setState(() {
                      _description = value!;
                    });
                  },
                  validator: (String? value) {
                    return (value == null || value.isEmpty)
                        ? "Description tidak boleh kosong!"
                        : (int.tryParse(value) != null)
                            ? "Description harus berupa kata!"
                            : null;
                  },
                ),
              ),
              Align(
                alignment: Alignment.bottomCenter,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: ElevatedButton(
                    style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all(const Color(0xFF12486B)),
                    ),
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        data.add(Salts(
                          _name,
                          _amount,
                          _description,
                          
                        ));
                        showDialog(
                            context: context,
                            builder: (context) {
                              return AlertDialog(
                                title: const Text(
                                  'Salt berhasil tersimpan',
                                  style: TextStyle(
                                    color: Colors.green,
                                  ),
                                ),
                                content: SingleChildScrollView(
                                  child: Column(
                                    crossAxisAlignment:
                                        CrossAxisAlignment.start,
                                    children: [
                                      Text('Name: $_name', style: const TextStyle(color: Colors.black),),
                                      Text('Amount: $_amount', style: const TextStyle(color: Colors.black),),
                                      Text('Description: $_description', style: const TextStyle(color: Colors.black),),
                                    ],
                                  ),
                                ),
                                actions: [
                                  TextButton(
                                      onPressed: () =>
                                          Navigator.pushReplacement(
                                              context,
                                              MaterialPageRoute(
                                                builder: (context) =>
                                                    MyHomePage(),
                                              )),
                                      child: const Text('SIP'))
                                ],
                              );
                            });
                        _formKey.currentState!.reset();
                      }
                    },
                    child: const Text(
                      "Save",
                      style: TextStyle(color: Colors.white),
                    ),
                  ),
                ),
              ),
              Align(
                alignment: Alignment.bottomCenter,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: ElevatedButton(
                    style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all(const Color(0xFF12486B)),
                    ),
                    onPressed: () async {
                      if (_formKey.currentState!.validate()) {
                        // Kirim ke Django dan tunggu respons
                        final response = await request.postJson(
                        "https://alden-luthfi-tugas.pbp.cs.ui.ac.id/create-flutter/",
                        jsonEncode(<String, String>{
                            'name': _name,
                            'amount': _amount.toString(),
                            'desc': _description,
                        }));
                        if (response['status'] == 'success') {
                            ScaffoldMessenger.of(context)
                                .showSnackBar(const SnackBar(
                            content: Text("Produk baru berhasil disimpan!"),
                            ));
                            Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(builder: (context) => MyHomePage()),
                            );
                        } else {
                            ScaffoldMessenger.of(context)
                                .showSnackBar(const SnackBar(
                                content:
                                    Text("Terdapat kesalahan, silakan coba lagi."),
                            )
                          );
                        }
                      }
                    },
                    child: const Text(
                      "Save DJANGO",
                      style: TextStyle(color: Colors.white),
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
