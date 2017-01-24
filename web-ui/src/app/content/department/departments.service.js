'use strict';

console.log('department service');

let vm;
const _$http = new WeakMap();
const _$q = new WeakMap();

class DepartmentsService {
    constructor($http, $q) {
        vm = this;
        _$http.set(vm, $http);
        _$q.set(vm, $q);
    }

    getAllDepartments() {
        return _$http.get(vm).get('/ajaxListDepartments');
    }

    removeDep(idDepartment) {
        return _$http.get(vm).post("ajaxRemoveDepartment", {idDepartment});
    }

    findByIdDepartment(idDepartment) {
        return _$http.get(vm)({
            url: 'ajaxFindByIdDepartment',
            method: "GET",
            params: {idDepartment}
        });
    }

    saveDep(idDepartment, departmentName) {
        return _$q.get(vm)((resolve, reject) => {
                _$http.get(vm).post("ajaxSaveNewDepartment", {
                    idDepartment,
                    departmentName: departmentName.trim()
                }).then((response) => {
                    resolve(response.data);
                }).catch((error) => {
                    reject(error);
                })
            }
        )
    }
}

DepartmentsService.$inject = ['$http', '$q'];

export default DepartmentsService;