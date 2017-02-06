'use strict';

let vm;
const _$http = new WeakMap();
const _$q = new WeakMap();

class EmployeesService {
    constructor($http, $q) {
        vm = this;
        _$http.set(vm, $http);
        _$q.set(vm, $q);
    }

    getAllEmployees(idDepartment) {
        return _$http.get(vm)({
            url: 'ajaxListEmployees',
            method: "GET",
            params: {
                idDepartment
            }
        });
    }

    removeEmpl(employeeId) {
        return _$http.get(vm).post('ajaxRemoveEmployee', {employeeId});
    }

    findById(employeeId) {
        return _$http.get(vm)({
            url: 'ajaxFindByIdEmployee',
            method: "GET",
            params: {
                employeeId
            }
        });
    }

    saveEmpl(employeeId, employeeName, employeeEmail, employeeSalary, employeeRegister, idDepartment) {
        let employee = {
            employeeId,
            employeeName: employeeName.trim(),
            employeeEmail: employeeEmail.trim().toLowerCase(),
            employeeSalary: employeeSalary.trim(),
            employeeRegister
        };
        return _$q.get(vm)((resolve, reject) => {
            _$http.get(vm)({
                method: 'POST',
                url: 'ajaxSaveNewEmployee',
                data: employee,
                params: {idDepartment}
            }).then((response) => {
                resolve(response.data);
            }).catch((error) => {
                reject(error);
            });
        })
    }
}

EmployeesService.$inject = ['$http', '$q'];

export default EmployeesService;