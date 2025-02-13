package main

import (
	"errors"
	"fmt"
)


var (
	Name      string = "Alden Luthfi"
	IdStudent string = "2206028932"
	Items     []Tool
	Members   []Tool
)

func AddItem(SKU string, itemName string, price int32, stockQty int32) (string, error) {

	for _, item := range Items {
		if item.(*Item).SKU == SKU {
			return "", fmt.Errorf("item %s is already in list of items", SKU)
		}
	}

	Items = append(Items, &Item{SKU, itemName, stockQty, []Transaction{}, price})

	return fmt.Sprintf("successfully added item %s to list of items", SKU), nil
}

func DeleteItem(SKU string) (string, error) {
	for i, item := range Items {
		if item.(*Item).SKU == SKU {

			if len(item.(*Item).Transactions) != 0 {
				return "", fmt.Errorf("there is at least one transaction taking item %s", SKU)
			}

			Items = append(Items[:i], Items[i+1:]...)
			return fmt.Sprintf("successfully deleted item %s from list of items", SKU), nil
		}
	}

	return "", errors.New("Item not found")
}

func AddMember(idMember string, memberName string) (string, error) {

	for _, member := range Members {
		if member.(*Member).IdMember == idMember {
			return "", fmt.Errorf("member %s is already in list of members", idMember)
		}
	}

	Members = append(Members, &Member{idMember, memberName, []Transaction{}})
	return fmt.Sprintf("successfully added member %s to list of members", idMember), nil
}

func DeleteMember(idMember string) (string, error) {
	for i, member := range Members {
		if member.(*Member).IdMember == idMember {

			if len(member.(*Member).Transactions) != 0 {
				return "", fmt.Errorf("there is at least one transaction taking member %s", idMember)
			}

			Members = append(Members[:i], Members[i+1:]...)
			return fmt.Sprintf("successfully deleted member %s from list of members", idMember), nil
		}
	}

	return "", fmt.Errorf("member %s is not in list of members", idMember)
}

func AddTransaction(qty int32, data ...string) (string, error) {
	if len(data) < 2 {
		for _, item := range Items {
			if item.(*Item).SKU == data[0] {
				if item.(*Item).StockQty < qty {
					return "", fmt.Errorf("stock qty for item %s is not enough", data[0])
				}

				item.(*Item).StockQty -= qty
				item.(*Item).AddTransaction(Transaction{nil, data[0], qty, item.(*Item).Price})
				return fmt.Sprintf("successfully added transaction item %s", data[0]), nil
			}
		}

		return "", fmt.Errorf("item %s is not in list of items", data[0])
	} else {
		for _, member := range Members {
			if member.(*Member).IdMember == data[1] {
				for _, item := range Items {
					if item.(*Item).SKU == data[0] {
						if item.(*Item).StockQty < qty {
							return "", fmt.Errorf("stock qty for item %s is not enough", data[0])
						}

						item.(*Item).StockQty -= qty
						item.(*Item).AddTransaction(Transaction{&data[1], data[0], qty, item.(*Item).Price})
						member.(*Member).AddTransaction(Transaction{&data[1], data[0], qty, item.(*Item).Price})
						return fmt.Sprintf("successfully added transaction item %s for member %s", data[0], data[1]), nil
					}
				}

				return "", fmt.Errorf("item %s is not in list of items", data[0])
			}
		}

		return "", fmt.Errorf("member %s is not in list of members", data[1])
	}
}

func RestockItem(SKU string, qty int32) (string, error) {
	for _, item := range Items {
		if item.(*Item).SKU == SKU {
			item.(*Item).StockQty += qty
			return fmt.Sprintf("successfully restock qty for item %s", SKU), nil
		}
	}

	return "", fmt.Errorf("item %s is not in list of items", SKU)
}

func GetTransactionItem(SKU string) ([]Transaction, error) {
	for _, item := range Items {
		if item.(*Item).SKU == SKU {
			return item.(*Item).Transactions, nil
		}
	}

	return nil, fmt.Errorf("item %s is not in list of items", SKU)
}

func GetTransactionMember(idMember string) ([]Transaction, error) {
	for _, member := range Members {
		if member.(*Member).IdMember == idMember {
			return member.(*Member).Transactions, nil
		}
	}

	return nil, fmt.Errorf("member %s is not in list of members", idMember)
}
