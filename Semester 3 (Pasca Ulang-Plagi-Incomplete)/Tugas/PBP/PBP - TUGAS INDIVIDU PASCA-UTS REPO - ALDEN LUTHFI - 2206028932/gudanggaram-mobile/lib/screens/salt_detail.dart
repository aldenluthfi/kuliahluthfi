import 'package:flutter/material.dart';
import 'package:gudang_garam/models/salt.dart';

class Detail extends StatelessWidget {
  final Salts item;

  const Detail(this.item, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Detail Item",
          style: TextStyle(color: Colors.white),
        ),
        backgroundColor: const Color(0xFF419197),
        foregroundColor: Colors.white,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(50.0),
          child: ItemCard(item.fields),
        ),
      ),
    );
  }
}

class ItemCard extends StatelessWidget {
  final Fields item;

  const ItemCard(this.item, {super.key});

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(20.0),
      child: Material(
        color: const Color(0xFF78D6C6),
        child: InkWell(
          onTap: () {},
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Padding(
                padding: const EdgeInsets.all(15.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
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
                          const SizedBox(height: 5,),
                        
                          Column(
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: [
                              const Text("HASH:", style: TextStyle(fontSize: 20),),
                              Text(
                                item.sha256Sum,
                                textAlign: TextAlign.center,
                                style: const TextStyle(color: Colors.white, fontSize: 20),
                              ),
                            ],
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
              Padding(
                padding: const EdgeInsets.all(20.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text("Description:"),
                    const SizedBox(height: 5,),
                    SizedBox(width: 300, child: Text(item.description)),
                    const SizedBox(
                      height: 20,
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
