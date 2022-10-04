const getters = {
    isLogged: state => state.isLogged,
    files: state => state.fileData.files,
    number_of_files: state => state.fileData.number_of_files
};

export default getters;