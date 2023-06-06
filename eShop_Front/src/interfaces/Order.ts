export interface Order {
    orderNumber: string;
    clientName: string;
    products: Array<string>;
    amount: number;
}