export interface ResponseOrderDTO {
    orderId: string;
    user: string;
    status: string;
    total: number;
    items: ResponseItemDTO[];
  }
  
  export interface ResponseItemDTO {
    itemId: string;
    name: string;
    description: string;
    price: number;
    imageUrl: string;
    category: string | null;
  }