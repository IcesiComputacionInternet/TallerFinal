import { TYPES } from "../actions/crudActions";

export const crudInitialState = {
  db: null,
};

interface Action {
  type: string;
  payload?: any;
}

export function crudReducer(state: any, action: Action) {
  switch (action.type) {
    case TYPES.READ_ALL_DATA:
      if (!action.payload) return crudInitialState;
      return {
        ...state,
        db: action.payload.map((data: unknown) => data),
      };
    case TYPES.CREATE_DATA:
      return {
        ...state,
        db: [...state.db, action.payload],
      };
    case TYPES.UPDATE_DATA: {
      if (!action.payload) return crudInitialState;
      const newData = state.db.map((el: any) => {
        el.id === action.payload.id ? action.payload : el;
      });
      return {
        ...state,
        db: newData,
      };
    }
    case TYPES.DELETE_DATA: {
      const dataDelete = state.db.filter((el: any) => el.id !== action.payload);
      return {
        ...state,
        db: dataDelete,
      };
    }
    case TYPES.NO_DATA:
      return crudInitialState;
    default:
      return state;
  }
}
