'use strict';

let vm;
const _$state = new WeakMap();
const _departmentsService = new WeakMap();

class UpdateDepartmentController {
    constructor($state, DepartmentsService, $scope) {
        vm = this;
        _$state.set(vm, $state);
        _departmentsService.set(vm, DepartmentsService);
        vm.departmentName = '';
        vm.idDepartment = null;

        if ($scope.$parent.$resolve.department !== null) {
            vm.departmentName = $scope.$parent.$resolve.department.data.departmentName;
            vm.idDepartment = $scope.$parent.$resolve.department.data.idDepartment;
        }
        vm.errorEnterNameDep = [];
    }

    saveDepartment() {
        if (vm.departmentName === undefined) {
            vm.departmentName = '';
        }
        let saveDepComplete = (response) => {
            if (response === "") {
                _$state.get(vm).go('mainPage');
            } else {
                vm.errorEnterNameDep = response;
                _$state.get(vm).go('editDepartment');
            }
        };
        let saveDepFailed = () => {
            vm.errorEnterNameDep = ['There was a problem with update'];
        };

        _departmentsService.get(vm).saveDep(vm.idDepartment, vm.departmentName)
            .then(saveDepComplete)
            .catch(saveDepFailed);
    }

    toMainPage() {
        _$state.get(vm).go('mainPage');
    }
}

UpdateDepartmentController.$inject = ['$state', 'DepartmentsService', '$scope'];

const UpdateDepartmentComponent = {
    controller: UpdateDepartmentController,
    controllerAs: 'updateDep',
    template: require('./update.department.html'),
    bindings: {
        departmentData: '='
    }
};

export default UpdateDepartmentComponent;