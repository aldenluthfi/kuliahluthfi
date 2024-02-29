import 'package:flutter/material.dart';
import 'package:gudang_garam/widgets/item_data.dart';

class SaltCard extends StatelessWidget {
  const SaltCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "List Item",
          style: TextStyle(color: Colors.white),
        ),
        backgroundColor: const Color(0xFF419197),
        foregroundColor: Colors.white,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(50.0),
          child: Column(
            children: data.map((Salts item) {
              return Container(
                margin: const EdgeInsets.only(bottom: 20.0),
                child: CardWidget(item),
              );
            }).toList(),
          ),
        ),
      ),
    );
  }
}

class CardWidget extends StatelessWidget {
  final Salts item;

  const CardWidget(this.item, {super.key});

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(20.0),
      child: Material(
        color: const Color(0xFF78D6C6),
        child: InkWell(
          onTap: () {},
          child: Column(
            children: [
              Padding(
                padding: const EdgeInsets.all(15.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    SizedBox(
                      width: 200.0,
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            item.name,
                            style: const TextStyle(color: Colors.white),
                          ),
                        ],
                      ),
                    ),
                    Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        Text(
                          "Amount: ${item.amount.toString()}",
                          style: const TextStyle(color: Colors.white),
                        ),
                      ],
                    )
                  ],
                ),
              ),
              Container(
                decoration: const BoxDecoration(color: Color(0xFF78D6C6)),
                width: 350,
                child: Row(
                  children: [
                    const SizedBox(width: 10,),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const Text("description:", style: TextStyle(color: Colors.white),),
                        Text(item.description, style: const TextStyle(color: Colors.white),),
                        const SizedBox(height: 10,),
                      ],
                    ),
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
