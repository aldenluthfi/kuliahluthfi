import 'package:flutter/material.dart';
import 'package:gudang_garam/models/user.dart';
import 'package:gudang_garam/screens/all_salt_dj.dart';
import 'package:gudang_garam/screens/form.dart';
import 'package:gudang_garam/screens/login.dart';
import 'package:gudang_garam/screens/salt_page.dart';
import 'package:gudang_garam/screens/user_salt_dj.dart';
import 'package:gudang_garam/widgets/drawer.dart';
import 'package:pbp_django_auth/pbp_django_auth.dart';
import 'package:provider/provider.dart';

class MyHomePage extends StatelessWidget {
  MyHomePage({Key? key}) : super(key: key);

  final List<ShopItem> items = [
    ShopItem("Lihat Item", Icons.view_in_ar, const Color(0xFF78D6C6)),
    ShopItem("Tambah Item", Icons.add_box, const Color(0xFF419197)),
    ShopItem("Lihat Semua Item", Icons.view_in_ar,
        const Color.fromARGB(255, 40, 112, 117)),
    ShopItem("Lihat Item Punya User", Icons.view_in_ar,
        const Color.fromARGB(255, 15, 92, 97)),
    ShopItem("Logout", Icons.logout_outlined, const Color(0xFF12486B)),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Gudang Garam'),
          backgroundColor: const Color(0xFFF5FCCD),
        ),
        drawer: const DrawerNavi(),
        body: SingleChildScrollView(
          child: Center(
            child: Column(
              children: <Widget>[
                Padding(
                  padding: EdgeInsets.only(top: 10.0, bottom: 10.0),
                  child: Text(
                    'bienvenue, ${loggedInUser.username}',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      fontSize: 30,
                      fontWeight: FontWeight.bold,
                      color: Color(0xFFFFC436),
                    ),
                  ),
                ),
                Column(
                  children: items.map((item) {
                    return SizedBox(
                      width: 300,
                      height: 200,
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: ShopCard(item),
                      ),
                    );
                  }).toList(),
                ),
              ],
            ),
          ),
        ));
  }
}

class ShopItem {
  final String name;
  final IconData iconFeat;
  final Color cardColor;

  ShopItem(this.name, this.iconFeat, this.cardColor);
}

class ShopCard extends StatelessWidget {
  final ShopItem feat;

  const ShopCard(this.feat, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final request = context.watch<CookieRequest>();
    return Material(
      color: feat.cardColor,
      borderRadius: BorderRadius.circular(12),
      child: InkWell(
        onTap: () async {
          if (feat.name == "Lihat Item") {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const SaltCard()),
            );
          } else if (feat.name == "Tambah Item") {
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => const AddForm()));
          } else if (feat.name == "Lihat Semua Item") {
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => const ItemPage()));
          } else if (feat.name == "Lihat Item Punya User") {
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => const UserItemPage()));
          } else if (feat.name == "Logout") {
            final response = await request.logout(
                "https://alden-luthfi-tugas.pbp.cs.ui.ac.id/auth/logout/");
            String message = response["message"];

            if (response['status']) {
              ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                content:
                    Text("$message Sampai jumpa, ${loggedInUser.username}."),
              ));
              Navigator.pushReplacement(
                context,
                MaterialPageRoute(builder: (context) => const LoginPage()),
              );
            } else {
              ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                content: Text(message),
              ));
            }
          }
        },
        child: Container(
          width: 240,
          padding: const EdgeInsets.all(8),
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Icon(
                  feat.iconFeat,
                  color: Colors.white,
                  size: 50.0,
                ),
                const Padding(padding: EdgeInsets.all(3)),
                Text(
                  feat.name,
                  textAlign: TextAlign.center,
                  style: const TextStyle(color: Colors.white, fontSize: 25),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
