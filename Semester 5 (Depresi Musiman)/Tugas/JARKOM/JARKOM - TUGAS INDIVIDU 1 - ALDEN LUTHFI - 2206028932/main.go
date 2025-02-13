package main

import (
	"bufio"
	"errors"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {

	fmt.Printf("Name: %s, ID Student: %s\n", Name, IdStudent)
	fmt.Println("========================================")
	fmt.Println("Welcome to Sigmart Point of Sales")
	fmt.Println("Please input your command below")
	fmt.Println("========================================")

	scanner := bufio.NewScanner(os.Stdin)
	for {
		scanner.Scan()
		line := scanner.Text()
		err := scanner.Err()
		if err != nil {
			fmt.Println("[CRASH] ", err.Error())
			os.Exit(1)
		}

		spl := strings.Split(line, " ")
		executeCommand(spl[0], spl[1:])
	}
}

func executeCommand(command string, data []string) {
	switch command {
		case "ADD_ITEM":
			if len(data) != 4 {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			price, e1 := strconv.Atoi(data[2])
			stockQty, e2 := strconv.Atoi(data[3])

			if e1 != nil || e2 != nil {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			PrintMessage(AddItem(data[0], data[1], int32(price), int32(stockQty)))
		case "DELETE_ITEM":
			if len(data) != 1 {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			PrintMessage(DeleteItem(data[0]))
		case "ADD_MEMBER":
			if len(data) != 2 {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			PrintMessage(AddMember(data[0], data[1]))
		case "DELETE_MEMBER":
			if len(data) != 1 {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			PrintMessage(DeleteMember(data[0]))
		case "ADD_TRANSACTION":
			if len(data) != 2 && len(data) != 3 {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			qty, e1 := strconv.Atoi(data[0])

			if e1 != nil {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			PrintMessage(AddTransaction(int32(qty), data[1:]...))
		case "RESTOCK_ITEM":
			if len(data) != 2 {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			qty, e1 := strconv.Atoi(data[1])

			if e1 != nil {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			PrintMessage(RestockItem(data[0], int32(qty)))
		case "TRANSACTION_ITEM_RECAP":
			if len(data) != 1 {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			PrintTransactionRecap(GetTransactionItem(data[0]))
		case "TRANSACTION_MEMBER_RECAP":
			if len(data) != 1 {
				PrintMessage("", errors.New("your input command is incorrect"))
				break
			}

			PrintTransactionRecap(GetTransactionMember(data[0]))
		case "EXIT":
			os.Exit(1)
		default:
			os.Exit(1)
		}
}

func PrintMessage(successMsg string, errMsg error) {
	if errMsg != nil {
		fmt.Printf("[FAILED] %s\n", errMsg)
	} else {
		fmt.Printf("[SUCCESS] %s\n", successMsg)
	}
}

func PrintTransactionRecap(transactions []Transaction, errMsg error) {
	if errMsg != nil {
		fmt.Printf("[FAILED] %s\n", errMsg)
	} else if len(transactions) == 0 {
		fmt.Println("[FAILED] does not have transaction data")
	} else {
		fmt.Println("-x-x-x-x-x-x-x-x-x-x-x-x-")

		for _, transaction := range transactions {
			id := transaction.IdMember

			if id == nil {
				id = new(string)
				*id = "-"
			}

			fmt.Printf(
				"SKU: %s, ID Member: %s, Qty: %d, Total Price: %d\n",
				transaction.SKU, *id, transaction.Qty, transaction.Qty * transaction.Price)
		}

		fmt.Println("-x-x-x-x-x-x-x-x-x-x-x-x-")
	}

}
