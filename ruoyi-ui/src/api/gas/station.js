import request from '@/utils/request'

// 查询用户所有的加油站信息
export function listStation() {
    return request({
        url: '/gas/station/list',
        method: 'get'
      })
}

// 增加用户加油站
export function addStation(data) {
  return request({
      url: '/gas/station/add',
      method: 'post',
      data: data
    })
}

// 删除用户加油站
export function deleteStation(stationId) {
  return request({
      url: `/gas/station/remove/${stationId}`,
      method: 'post'
    })
}


// 修改用户加油站状态
export function changeStationStatus(stationId, status) {
  return request({
      url: `/gas/station/${stationId}/status/${status}`,
      method: 'post'
    })
}

// 上传加油站URL
export function uploadAction() {
  let baseUrl = process.env.VUE_APP_BASE_API
  return `${baseUrl}/gas/station/import`
}
