import request from '@/utils/request'

/** 查询对手加油站列表 */
export function listOpponentStation (gasStationId) {
    return request({
        url: '/gas/opponent-station/list',
        method: 'get',
        params: {gasStationId: gasStationId}
    })
}

/** 修改对手加油站状态 */
export function changeStatus (messageId, status) {
    return request({
        url: `/gas/opponent-station/${messageId}/status/${status}`,
        method: 'post'
    })
}

/** 修改加油站名称 */
export function changeStationName (newName) {
    return request({
        url: `/gas/opponent-station/${messageId}/name`,
        method: 'post',
        params: {newName: newName}
    })
}

/** 对手价格上传接口 */
export function importOpponentPrice () {
    let baseUrl = process.env.VUE_APP_BASE_API
    return `${baseUrl}/gas/opponent-price/import`
}
