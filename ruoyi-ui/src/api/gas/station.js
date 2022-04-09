import request from '@/utils/request'

// 查询用户所有的加油站信息
export function listStation() {
    return request({
        url: '/gas/station/list',
        method: 'get'
      })
}
