'use strict';

import {user} from "../config";

let vm;
const _$state = new WeakMap();
const _$uibModal = new WeakMap();
const _notification = new WeakMap();
const _departmentsService = new WeakMap();

class ListDepartmentsController {
    constructor($state, $uibModal, Notification, DepartmentsService, $scope) {
        vm = this;
        _$state.set(vm, $state);
        _$uibModal.set(vm, $uibModal);
        _notification.set(vm, Notification);
        _departmentsService.set(vm, DepartmentsService);
        vm.departments = $scope.$parent.$resolve.departments.data;
        vm.department = {};
        vm.init();
    }

    init() {
        vm.name = user;
    }

    addDepartment() {
        _$state.get(vm).go('editDepartment', {
            idDepartment: null
        });
    }

    editDepartment(idDepartment) {
        _$state.get(vm).go('editDepartment', {idDepartment});
    }

    removeDepartment(department) {
        vm.modalInstance = _$uibModal.get(vm).open({
            component: 'removeDepartment',
            resolve: {
                idDepartment: department.idDepartment
            }
        });

        vm.modalInstance.result.then((result) => {
                if (result.message === 'OK') {
                    _departmentsService.get(vm).getAllDepartments()
                        .then((response) => {
                            vm.departments = response.data;
                        });
                    // _notification.get(vm).success({
                    //     title: 'Success!',
                    //     message: `Department with name ${department.departmentName} was deleted!`
                    // });
                } else if (result.message === 'Error') {
                    // _notification.get(vm)
                    //     .error({
                    //         title: 'Error!',
                    //         message: `Department with name ${department.departmentName} wasn't found!`
                    //     });
                }
            }
        );
    }

    listEmployees(idDepartment) {
        _$state.get(vm).go('listEmployees', {idDepartment});
    }
}

ListDepartmentsController.$inject = ['$state', '$uibModal', 'Notification', 'DepartmentsService', '$scope'];

const ListDepartmentsComponent = {
    controller: ListDepartmentsController,
    controllerAs: 'listDep',
    template: require('./list.departments.html'),
    bindings: {
        departmentsData: '<'
    }
};

export default ListDepartmentsComponent;