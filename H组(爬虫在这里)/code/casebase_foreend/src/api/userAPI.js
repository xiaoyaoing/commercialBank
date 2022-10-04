import {axios} from "../util/request";

const api = {
    userPre: "/api/user"
};

export function getUserInfo() {
    return axios({
        url: `${api.userPre}/getUserInfo`,
        method: "POST"
    });
}