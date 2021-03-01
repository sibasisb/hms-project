export const initialState = {
    userId: null,
    token: null,
    role: null
};

export const userReducer = (state, action) => {
    switch (action.type) {
        case "LOGIN":
            localStorage.setItem("userId", action.payload.userId);
            localStorage.setItem("token", action.payload.token);
            localStorage.setItem("role", action.payload.role);
            action.payload.hospitalId && localStorage.setItem("hospitalId",action.payload.hospitalId); 
            return {
                ...state,
                user: action.payload.user,
                token: action.payload.token,
                role:action.payload.role
            };
        case "LOGOUT":
            localStorage.clear();
            return {
                userId: null,
                token: null,
                role: null
               
            };
        default:
            return state;
    }
}

export default userReducer;