export const initialState = {
    isAuthenticated: false,
    userId: null,
    token: null,
    role: null
};

export const userReducer = (state, action) => {
    switch (action.type) {
        case "LOGIN":
            localStorage.setItem("userId", JSON.stringify(action.payload.userId));
            localStorage.setItem("token", JSON.stringify(action.payload.token));
            localStorage.setItem("role", JSON.stringify(action.payload.role));
            return {
                ...state,
                isAuthenticated: true,
                user: action.payload.user,
                token: action.payload.token
            };
        case "LOGOUT":
            localStorage.clear();
            return {
                ...state,
                isAuthenticated: false,
                user: null
            };
        default:
            return state;
    }
}

export default userReducer;