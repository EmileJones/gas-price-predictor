import request from '@/utils/request'

// 查询加油站周期列表
export function listPeriod (query) {
    return request({
        url: '/gas/user-period/list',
        method: 'get',
        params: query
    })
}

// 查询加油站周期详细
export function getPeriod (id) {
    return request({
        url: '/gas/user-period/' + id,
        method: 'get'
    })
}

// 新增加油站周期
export function addPeriod (data) {
    return request({
        url: '/gas/user-period',
        method: 'post',
        data: data
    })
}

// 修改加油站周期
export function updatePeriod (data) {
    return request({
        url: '/gas/user-period',
        method: 'put',
        data: data
    })
}

// 删除加油站周期
export function delPeriod (id) {
    return request({
        url: '/gas/user-period/' + id,
        method: 'delete'
    })
}
