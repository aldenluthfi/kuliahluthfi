package main

type Tool interface {
	AddTransaction(data any)
	GetData() any
}

type Transaction struct {
	IdMember *string
	SKU      string
	Qty      int32
	Price    int32
}

type Member struct {
	IdMember     string
	MemberName   string
	Transactions []Transaction
}

func (m *Member) AddTransaction(data any) {
	for _, transaction := range m.Transactions {
		if data.(Transaction).SKU == transaction.SKU {
			transaction.Qty += data.(Transaction).Qty
			return
		}
	}
	m.Transactions = append(m.Transactions, data.(Transaction))
}

func (m *Member) GetData() any {
	return m
}

type Item struct {
	SKU          string
	ItemName     string
	StockQty     int32
	Transactions []Transaction
	Price        int32
}

func (it *Item) AddTransaction(data any) {
	for _, transaction := range it.Transactions {
		if data.(Transaction).SKU == transaction.SKU {
			transaction.Qty += data.(Transaction).Qty
			return
		}
	}
	it.Transactions = append(it.Transactions, data.(Transaction))
}

func (it *Item) GetData() any {
	return it
}
