import {axios} from "../util/request";

const api = {
    fileDataPre: "/api/document"
};

export function getFilesAPI(data) {
    return axios({
        url: `${api.fileDataPre}/getFiles`,
        method: "POST",
        data: data
    });
}

export function addFileAPI(data) {
    return axios({
        url: `${api.fileDataPre}/addFile`,
        method: "POST",
        data
    });
}

export function modFileAPI(data) {
    return axios({
        url: `${api.fileDataPre}/modFile`,
        method: "POST",
        data: data
    });
}

export function delFilesAPI(data) {
    return axios({
        url: `${api.fileDataPre}/delFiles`,
        method: "POST",
        data: data
    });
}

export function uploadFilesAPI(data) {
    console.log(data);
    return axios({
        url: `${api.fileDataPre}/uploadFiles`,
        method: "POST",
        data
    });
}

export function searchFilesAPI(data) {
    console.log(data);
    return axios({
        url: `${api.fileDataPre}/getFiles`,
        method: "POST",
        data
    });
}

export function getFileByIdAPI(id) {
    return axios({
        url: `${api.fileDataPre}/getFileById/${id}`,
        method: "GET"
    });
}

export function modFileStateAPI(data) {
    return axios({
        url: `${api.fileDataPre}/release`,
        method: "POST",
        data
    });
}
