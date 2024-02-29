import 'dart:convert';

List<Salts> saltsFromJson(String str) => List<Salts>.from(json.decode(str).map((x) => Salts.fromJson(x)));

String saltsToJson(List<Salts> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class Salts {
    String model;
    int pk;
    Fields fields;

    Salts({
        required this.model,
        required this.pk,
        required this.fields,
    });

    factory Salts.fromJson(Map<String, dynamic> json) => Salts(
        model: json["model"],
        pk: json["pk"],
        fields: Fields.fromJson(json["fields"]),
    );

    Map<String, dynamic> toJson() => {
        "model": model,
        "pk": pk,
        "fields": fields.toJson(),
    };
}

class Fields {
    String name;
    int user;
    int amount;
    String description;
    String sha256Sum;

    Fields({
        required this.name,
        required this.user,
        required this.amount,
        required this.description,
        required this.sha256Sum,
    });

    factory Fields.fromJson(Map<String, dynamic> json) => Fields(
        name: json["name"],
        user: json["user"],
        amount: json["amount"],
        description: json["description"],
        sha256Sum: json["sha256sum"],
    );

    Map<String, dynamic> toJson() => {
        "name": name,
        "user": user,
        "amount": amount,
        "description": description,
        "sha256sum": sha256Sum,
    };
}
